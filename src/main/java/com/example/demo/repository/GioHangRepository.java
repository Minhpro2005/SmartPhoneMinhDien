package com.example.demo.repository;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    // ✅ Tìm giỏ hàng theo khách hàng
    List<GioHang> findByKhachHang(KhachHang khachHang);

    // ✅ Tìm giỏ hàng theo khách hàng và trạng thái
    List<GioHang> findByKhachHangAndTrangThai(KhachHang khachHang, boolean trangThai);

    // ✅ Tìm tất cả giỏ hàng theo trạng thái
    List<GioHang> findByTrangThai(boolean trangThai);
}
