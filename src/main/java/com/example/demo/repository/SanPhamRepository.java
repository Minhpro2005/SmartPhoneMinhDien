package com.example.demo.repository;

import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    // Tìm sản phẩm theo tên (chứa từ khoá)
    List<SanPham> findByTenSPContainingIgnoreCase(String keyword);

    // Tìm theo hãng
    List<SanPham> findByHang(String hang);

    // Tìm theo màu sắc
    List<SanPham> findByMauSac(String mauSac);

    // Tìm theo trạng thái (đang bán hay ngừng bán)
    List<SanPham> findByTrangThai(boolean trangThai);
}
