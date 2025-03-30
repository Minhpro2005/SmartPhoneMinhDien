package com.example.demo.service;

import com.example.demo.model.KhuyenMai;
import com.example.demo.repository.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    // Lấy tất cả khuyến mãi
    public List<KhuyenMai> getAll() {
        return khuyenMaiRepository.findAll();
    }

    // Lấy khuyến mãi theo ID
    public Optional<KhuyenMai> getById(int id) {
        return khuyenMaiRepository.findById(id);
    }

    // Thêm khuyến mãi mới
    public KhuyenMai create(KhuyenMai kh) {
        return khuyenMaiRepository.save(kh);
    }

    // Cập nhật khuyến mãi
    public KhuyenMai update(int id, KhuyenMai updated) {
        Optional<KhuyenMai> existingOpt = khuyenMaiRepository.findById(id);
        if (existingOpt.isPresent()) {
            KhuyenMai existing = existingOpt.get();
            existing.setTenKM(updated.getTenKM());
            existing.setGiamGia(updated.getGiamGia());
            existing.setNgayBatDau(updated.getNgayBatDau());
            existing.setNgayKetThuc(updated.getNgayKetThuc());
            return khuyenMaiRepository.save(existing);
        }
        return null;
    }

    // Xoá khuyến mãi
    public void delete(int id) {
        khuyenMaiRepository.deleteById(id);
    }

    // Tìm kiếm theo tên
    public List<KhuyenMai> searchByTen(String keyword) {
        return khuyenMaiRepository.findByTenKMContainingIgnoreCase(keyword);
    }

    // Tìm theo khoảng ngày bắt đầu
    public List<KhuyenMai> searchByNgayBatDau(Date start, Date end) {
        return khuyenMaiRepository.findByNgayBatDauBetween(start, end);
    }

    // Tìm theo khoảng ngày kết thúc
    public List<KhuyenMai> searchByNgayKetThuc(Date start, Date end) {
        return khuyenMaiRepository.findByNgayKetThucBetween(start, end);
    }
}
