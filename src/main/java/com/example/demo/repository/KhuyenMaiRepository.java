package com.example.demo.repository;

import com.example.demo.model.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {

    // Tìm khuyến mãi theo tên (chứa từ khoá, không phân biệt hoa thường)
    List<KhuyenMai> findByTenKMContainingIgnoreCase(String keyword);

    // Tìm khuyến mãi theo khoảng ngày bắt đầu
    List<KhuyenMai> findByNgayBatDauBetween(Date start, Date end);

    // Tìm khuyến mãi theo khoảng ngày kết thúc
    List<KhuyenMai> findByNgayKetThucBetween(Date start, Date end);
}
