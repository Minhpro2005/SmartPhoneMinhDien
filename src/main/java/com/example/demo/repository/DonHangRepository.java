package com.example.demo.repository;

import com.example.demo.model.DonHang;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {

    // ✅ Tìm đơn hàng theo khách hàng
    List<DonHang> findByKhachHang(KhachHang khachHang);

    // ✅ Tìm đơn hàng theo trạng thái
    List<DonHang> findByTrangThai(String trangThai);

    // ✅ Tìm đơn hàng theo khách hàng và trạng thái
    List<DonHang> findByKhachHangAndTrangThai(KhachHang khachHang, String trangThai);
}
