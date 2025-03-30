package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

    // ✅ Lấy chi tiết theo hóa đơn
    List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);

    // ✅ Lấy chi tiết theo sản phẩm
    List<HoaDonChiTiet> findBySanPham(SanPham sanPham);

    // ✅ Tìm theo hóa đơn và sản phẩm (nếu cần kiểm tra trùng)
    HoaDonChiTiet findByHoaDonAndSanPham(HoaDon hoaDon, SanPham sanPham);
}
