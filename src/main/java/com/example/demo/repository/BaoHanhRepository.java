package com.example.demo.repository;

import com.example.demo.model.BaoHanh;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaoHanhRepository extends JpaRepository<BaoHanh, Integer> {

    // ✅ Lấy danh sách bảo hành theo khách hàng
    List<BaoHanh> findByKhachHang(KhachHang khachHang);

    // ✅ Lấy danh sách bảo hành theo sản phẩm
    List<BaoHanh> findBySanPham(SanPham sanPham);

    // ✅ Lấy danh sách bảo hành theo trạng thái
    List<BaoHanh> findByTrangThai(boolean trangThai);
}
