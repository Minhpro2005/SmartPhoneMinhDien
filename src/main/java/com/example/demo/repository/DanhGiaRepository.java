package com.example.demo.repository;

import com.example.demo.model.DanhGia;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {

    // ✅ Lấy danh sách đánh giá theo khách hàng
    List<DanhGia> findByKhachHang(KhachHang khachHang);

    // ✅ Lấy danh sách đánh giá theo sản phẩm
    List<DanhGia> findBySanPham(SanPham sanPham);

    // ✅ Lấy đánh giá theo sao
    List<DanhGia> findBySoSao(int soSao);
}
