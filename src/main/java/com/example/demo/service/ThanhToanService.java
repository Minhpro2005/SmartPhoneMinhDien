package com.example.demo.service;

import com.example.demo.model.DonHang;
import com.example.demo.model.ThanhToan;
import com.example.demo.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThanhToanService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    // ✅ Lấy tất cả thanh toán
    public List<ThanhToan> getAll() {
        return thanhToanRepository.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<ThanhToan> getById(int id) {
        return thanhToanRepository.findById(id);
    }

    // ✅ Thêm mới
    public ThanhToan create(ThanhToan thanhToan) {
        if (thanhToan.getNgayThanhToan() == null) {
            thanhToan.setNgayThanhToan(new Date()); // Mặc định là ngày hiện tại
        }
        if (thanhToan.getTrangThai() == null || thanhToan.getTrangThai().isEmpty()) {
            thanhToan.setTrangThai("Chưa thanh toán");
        }
        return thanhToanRepository.save(thanhToan);
    }

    // ✅ Cập nhật
    public ThanhToan update(int id, ThanhToan updated) {
        Optional<ThanhToan> existingOpt = thanhToanRepository.findById(id);
        if (existingOpt.isPresent()) {
            ThanhToan existing = existingOpt.get();
            existing.setDonHang(updated.getDonHang());
            existing.setSoTien(updated.getSoTien());
            existing.setNgayThanhToan(updated.getNgayThanhToan());
            existing.setPhuongThucThanhToan(updated.getPhuongThucThanhToan());
            existing.setTrangThai(updated.getTrangThai());
            return thanhToanRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá theo ID
    public void delete(int id) {
        thanhToanRepository.deleteById(id);
    }

    // ✅ Lấy theo đơn hàng
    public List<ThanhToan> getByDonHang(DonHang donHang) {
        return thanhToanRepository.findByDonHang(donHang);
    }

    // ✅ Lấy theo trạng thái
    public List<ThanhToan> getByTrangThai(String trangThai) {
        return thanhToanRepository.findByTrangThai(trangThai);
    }

    // ✅ Lấy theo phương thức
    public List<ThanhToan> getByPhuongThuc(String phuongThuc) {
        return thanhToanRepository.findByPhuongThucThanhToan(phuongThuc);
    }
}
