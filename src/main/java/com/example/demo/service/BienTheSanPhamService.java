package com.example.demo.service;

import com.example.demo.model.BienTheSanPham;
import com.example.demo.model.SanPham;
import com.example.demo.repository.BienTheSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BienTheSanPhamService {

    @Autowired
    private BienTheSanPhamRepository bienTheRepo;

    // ✅ Lấy tất cả biến thể
    public List<BienTheSanPham> getAll() {
        return bienTheRepo.findAll();
    }

    // ✅ Lấy biến thể theo ID
    public Optional<BienTheSanPham> getById(int id) {
        return bienTheRepo.findById(id);
    }

    // ✅ Thêm mới biến thể
    public BienTheSanPham create(BienTheSanPham bts) {
        return bienTheRepo.save(bts);
    }

    // ✅ Cập nhật biến thể
    public BienTheSanPham update(int id, BienTheSanPham updated) {
        Optional<BienTheSanPham> existingOpt = bienTheRepo.findById(id);
        if (existingOpt.isPresent()) {
            BienTheSanPham existing = existingOpt.get();
            existing.setSanPham(updated.getSanPham());
            existing.setTenBienThe(updated.getTenBienThe());
            existing.setSoLuong(updated.getSoLuong());
            existing.setGiaBan(updated.getGiaBan());
            existing.setTrangThai(updated.isTrangThai());
            existing.setHinhAnh(updated.getMauSac());
            existing.setHinhAnh(updated.getHinhAnh());
            return bienTheRepo.save(existing);
        }
        return null;
    }

    // ✅ Xoá biến thể
    public void delete(int id) {
        bienTheRepo.deleteById(id);
    }

    // ✅ Tìm theo tên biến thể
    public List<BienTheSanPham> searchByTen(String keyword) {
        return bienTheRepo.findByTenBienTheContainingIgnoreCase(keyword);
    }

    // ✅ Lấy tất cả biến thể theo sản phẩm cha
    public List<BienTheSanPham> getBySanPham(SanPham sp) {
        return bienTheRepo.findBySanPham(sp);
    }

    // ✅ Lọc theo trạng thái
    public List<BienTheSanPham> getByTrangThai(boolean trangThai) {
        return bienTheRepo.findByTrangThai(trangThai);
    }
}
