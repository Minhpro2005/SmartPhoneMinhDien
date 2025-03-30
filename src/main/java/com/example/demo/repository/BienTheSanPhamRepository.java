package com.example.demo.repository;

import com.example.demo.model.BienTheSanPham;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienTheSanPhamRepository extends JpaRepository<BienTheSanPham, Integer> {

    // Lấy tất cả biến thể của một sản phẩm
    List<BienTheSanPham> findBySanPham(SanPham sanPham);

    // Tìm theo tên biến thể
    List<BienTheSanPham> findByTenBienTheContainingIgnoreCase(String keyword);

    // Tìm theo trạng thái
    List<BienTheSanPham> findByTrangThai(boolean trangThai);
}
