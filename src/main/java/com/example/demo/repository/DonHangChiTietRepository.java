package com.example.demo.repository;

import com.example.demo.model.DonHangChiTiet;
import com.example.demo.model.DonHang;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangChiTietRepository extends JpaRepository<DonHangChiTiet, Integer> {

    // ✅ Lấy danh sách chi tiết theo đơn hàng
    List<DonHangChiTiet> findByDonHang(DonHang donHang);

    // ✅ Lấy danh sách chi tiết theo sản phẩm
    List<DonHangChiTiet> findBySanPham(SanPham sanPham);

    // ✅ Lấy chi tiết theo đơn hàng và sản phẩm
    DonHangChiTiet findByDonHangAndSanPham(DonHang donHang, SanPham sanPham);

    // ✅ Xoá chi tiết theo đơn hàng
    void deleteByDonHang(DonHang donHang);
}
