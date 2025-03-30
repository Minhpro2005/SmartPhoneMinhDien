package com.example.demo.service;

import com.example.demo.model.DonHang;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    // ✅ Lấy tất cả đơn hàng
    public List<DonHang> getAll() {
        return donHangRepository.findAll();
    }

    // ✅ Lấy đơn hàng theo ID
    public Optional<DonHang> getById(int id) {
        return donHangRepository.findById(id);
    }

    // ✅ Tạo mới đơn hàng
    public DonHang create(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    // ✅ Cập nhật đơn hàng
    public DonHang update(int id, DonHang updated) {
        Optional<DonHang> existingOpt = donHangRepository.findById(id);
        if (existingOpt.isPresent()) {
            DonHang existing = existingOpt.get();
            existing.setKhachHang(updated.getKhachHang());
            existing.setNgayDat(updated.getNgayDat());
            existing.setTongTien(updated.getTongTien());
            existing.setPhuongThucThanhToan(updated.getPhuongThucThanhToan());
            existing.setTrangThai(updated.getTrangThai());
            existing.setDiaChiGiaoHang(updated.getDiaChiGiaoHang());
            existing.setSdtGiaoHang(updated.getSdtGiaoHang());
            return donHangRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá đơn hàng theo ID
    public void delete(int id) {
        donHangRepository.deleteById(id);
    }

    // ✅ Lấy đơn hàng theo khách hàng
    public List<DonHang> getByKhachHang(KhachHang khachHang) {
        return donHangRepository.findByKhachHang(khachHang);
    }

    // ✅ Lọc theo trạng thái
    public List<DonHang> getByTrangThai(String trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }

    // ✅ Lọc theo khách hàng và trạng thái
    public List<DonHang> getByKhachHangAndTrangThai(KhachHang khachHang, String trangThai) {
        return donHangRepository.findByKhachHangAndTrangThai(khachHang, trangThai);
    }
}
