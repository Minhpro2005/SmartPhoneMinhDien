package com.example.demo.service;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    // ✅ Lấy tất cả giỏ hàng
    public List<GioHang> getAll() {
        return gioHangRepository.findAll();
    }

    // ✅ Lấy giỏ hàng theo ID
    public Optional<GioHang> getById(int id) {
        return gioHangRepository.findById(id);
    }

    // ✅ Thêm mới giỏ hàng
    public GioHang create(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    // ✅ Cập nhật giỏ hàng
    public GioHang update(int id, GioHang updated) {
        Optional<GioHang> existingOpt = gioHangRepository.findById(id);
        if (existingOpt.isPresent()) {
            GioHang existing = existingOpt.get();
            existing.setKhachHang(updated.getKhachHang());
            existing.setNgayTao(updated.getNgayTao());
            existing.setTrangThai(updated.isTrangThai());
            return gioHangRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá giỏ hàng
    public void delete(int id) {
        gioHangRepository.deleteById(id);
    }

    // ✅ Lấy giỏ hàng theo khách hàng
    public List<GioHang> getByKhachHang(KhachHang khachHang) {
        return gioHangRepository.findByKhachHang(khachHang);
    }

    // ✅ Lấy giỏ hàng theo khách hàng và trạng thái
    public List<GioHang> getByKhachHangAndTrangThai(KhachHang khachHang, boolean trangThai) {
        return gioHangRepository.findByKhachHangAndTrangThai(khachHang, trangThai);
    }

    // ✅ Lọc giỏ hàng theo trạng thái
    public List<GioHang> getByTrangThai(boolean trangThai) {
        return gioHangRepository.findByTrangThai(trangThai);
    }
}
