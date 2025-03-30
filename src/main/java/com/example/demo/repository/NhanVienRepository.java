package com.example.demo.repository;

import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    // Tìm theo email
    NhanVien findByEmail(String email);

    // Tìm theo số điện thoại
    List<NhanVien> findBySdt(String sdt);

    // Tìm theo tên chứa từ khoá (không phân biệt hoa thường)
    List<NhanVien> findByHoTenContainingIgnoreCase(String keyword);

    // Tìm theo căn cước công dân
    NhanVien findByCccd(String cccd);
}
