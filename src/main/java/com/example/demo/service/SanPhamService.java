package com.example.demo.service;

import com.example.demo.model.SanPham;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    // ✅ Lấy tất cả sản phẩm
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<SanPham> getById(int id) {
        return sanPhamRepository.findById(id);
    }

    // ✅ Thêm mới sản phẩm
    public SanPham create(SanPham sp) {
        return sanPhamRepository.save(sp);
    }

    // ✅ Cập nhật sản phẩm
    public SanPham update(int id, SanPham updated) {
        Optional<SanPham> existingOpt = sanPhamRepository.findById(id);
        if (existingOpt.isPresent()) {
            SanPham existing = existingOpt.get();
            existing.setTenSP(updated.getTenSP());
            existing.setHang(updated.getHang());
            existing.setMauSac(updated.getMauSac());
            existing.setMoTa(updated.getMoTa());
            existing.setSoLuong(updated.getSoLuong());
            existing.setGiaBan(updated.getGiaBan());
            existing.setHinhAnhSP(updated.getHinhAnhSP());
            existing.setTrangThai(updated.isTrangThai());
            return sanPhamRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá sản phẩm
    public void delete(int id) {
        sanPhamRepository.deleteById(id);
    }

    // ✅ Tìm theo tên
    public List<SanPham> searchByTen(String keyword) {
        return sanPhamRepository.findByTenSPContainingIgnoreCase(keyword);
    }

    // ✅ Tìm theo hãng
    public List<SanPham> searchByHang(String hang) {
        return sanPhamRepository.findByHang(hang);
    }

    // ✅ Tìm theo màu sắc
    public List<SanPham> searchByMauSac(String mau) {
        return sanPhamRepository.findByMauSac(mau);
    }

    // ✅ Tìm theo trạng thái (đang bán / ngừng bán)
    public List<SanPham> searchByTrangThai(boolean trangThai) {
        return sanPhamRepository.findByTrangThai(trangThai);
    }
}
