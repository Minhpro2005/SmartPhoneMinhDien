package com.example.demo.service;

import com.example.demo.model.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    // Lấy toàn bộ khách hàng
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    // Lấy khách hàng theo ID
    public Optional<KhachHang> getKhachHangById(int id) {
        return khachHangRepository.findById(id);
    }

    // Thêm khách hàng mới
    public KhachHang createKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    // Cập nhật khách hàng
    public KhachHang updateKhachHang(int id, KhachHang updatedKH) {
        Optional<KhachHang> existingOpt = khachHangRepository.findById(id);
        if (existingOpt.isPresent()) {
            KhachHang existing = existingOpt.get();
            existing.setTenKH(updatedKH.getTenKH());
            existing.setSdt(updatedKH.getSdt());
            existing.setDiaChi(updatedKH.getDiaChi());
            existing.setEmail(updatedKH.getEmail());
            existing.setUser(updatedKH.getUser());
            return khachHangRepository.save(existing);
        }
        return null;
    }

    // Xoá khách hàng
    public void deleteKhachHang(int id) {
        khachHangRepository.deleteById(id);
    }

    // Tìm khách theo tên
    public List<KhachHang> searchByTen(String keyword) {
        return khachHangRepository.findByTenKHContainingIgnoreCase(keyword);
    }

    // Tìm theo SDT
    public List<KhachHang> searchBySdt(String sdt) {
        return khachHangRepository.findBySdt(sdt);
    }

    // Tìm theo Email
    public KhachHang searchByEmail(String email) {
        return khachHangRepository.findByEmail(email);
    }
}
