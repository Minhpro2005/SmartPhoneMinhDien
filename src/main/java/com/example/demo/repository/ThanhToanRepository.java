package com.example.demo.repository;

import com.example.demo.model.DonHang;
import com.example.demo.model.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {

    // ✅ Lấy danh sách thanh toán theo đơn hàng
    List<ThanhToan> findByDonHang(DonHang donHang);

    // ✅ Lấy thanh toán theo trạng thái
    List<ThanhToan> findByTrangThai(String trangThai);

    // ✅ Lấy theo phương thức thanh toán
    List<ThanhToan> findByPhuongThucThanhToan(String phuongThucThanhToan);
}
