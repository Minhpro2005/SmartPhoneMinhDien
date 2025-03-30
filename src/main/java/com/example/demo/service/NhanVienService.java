package com.example.demo.service;

import com.example.demo.model.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // Lấy tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    // Lấy nhân viên theo ID
    public Optional<NhanVien> getNhanVienById(int id) {
        return nhanVienRepository.findById(id);
    }

    // Thêm nhân viên mới
    public NhanVien createNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    // Cập nhật nhân viên
    public NhanVien updateNhanVien(int id, NhanVien updated) {
        Optional<NhanVien> existingOpt = nhanVienRepository.findById(id);
        if (existingOpt.isPresent()) {
            NhanVien existing = existingOpt.get();
            existing.setHoTen(updated.getHoTen());
            existing.setEmail(updated.getEmail());
            existing.setMatKhau(updated.getMatKhau());
            existing.setNgaySinh(updated.getNgaySinh());
            existing.setSdt(updated.getSdt());
            existing.setCccd(updated.getCccd());
            existing.setVaiTro(updated.getVaiTro());
            existing.setHinhAnh(updated.getHinhAnh());
            existing.setUser(updated.getUser());
            return nhanVienRepository.save(existing);
        }
        return null;
    }

    // Xoá nhân viên
    public void deleteNhanVien(int id) {
        nhanVienRepository.deleteById(id);
    }

    // Tìm theo tên (chứa keyword)
    public List<NhanVien> searchByTen(String keyword) {
        return nhanVienRepository.findByHoTenContainingIgnoreCase(keyword);
    }

    // Tìm theo email
    public NhanVien searchByEmail(String email) {
        return nhanVienRepository.findByEmail(email);
    }

    // Tìm theo SDT
    public List<NhanVien> searchBySdt(String sdt) {
        return nhanVienRepository.findBySdt(sdt);
    }

    // Tìm theo CCCD
    public NhanVien searchByCccd(String cccd) {
        return nhanVienRepository.findByCccd(cccd);
    }
}
