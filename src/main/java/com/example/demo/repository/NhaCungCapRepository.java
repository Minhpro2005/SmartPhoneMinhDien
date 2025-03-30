package com.example.demo.repository;

import com.example.demo.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {

    // Tìm theo tên (chứa từ khoá, không phân biệt hoa thường)
    List<NhaCungCap> findByTenNCCContainingIgnoreCase(String keyword);

    // Tìm theo SĐT
    List<NhaCungCap> findBySdt(String sdt);

    // Tìm theo Email
    NhaCungCap findByEmail(String email);
}
