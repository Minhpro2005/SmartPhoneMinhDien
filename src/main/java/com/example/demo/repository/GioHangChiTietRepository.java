package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.SanPham;

import java.util.List;


@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

    /**
     * Tìm tất cả chi tiết trong một giỏ hàng.
     * @param gioHang thực thể Giỏ hàng để tìm các chi tiết.
     * @return Danh sách GioHangChiTiet thuộc giỏ hàng này.
     */
    List<GioHangChiTiet> findByGioHang(GioHang gioHang);

    /**
     * Tìm tất cả chi tiết có chứa một sản phẩm cụ thể.
     * @param sanPham thực thể Sản phẩm để tìm các chi tiết.
     * @return Danh sách GioHangChiTiet chứa sản phẩm này.
     */
    List<GioHangChiTiet> findBySanPham(SanPham sanPham);

    /**
     * Tìm một chi tiết giỏ hàng cụ thể theo giỏ hàng và sản phẩm.
     * @param gioHang thực thể Giỏ hàng.
     * @param sanPham thực thể Sản phẩm.
     * @return GioHangChiTiet tương ứng nếu tồn tại (null nếu không tìm thấy).
     */
    GioHangChiTiet findByGioHangAndSanPham(GioHang gioHang, SanPham sanPham);

    /**
     * Xóa tất cả chi tiết thuộc về một giỏ hàng.
     * @param gioHang thực thể Giỏ hàng cần xóa các chi tiết.
     */
    void deleteByGioHang(GioHang gioHang);
}
