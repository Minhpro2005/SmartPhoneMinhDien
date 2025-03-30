package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.SanPham;
import com.example.demo.repository.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository chiTietRepo;

    // ✅ Lấy tất cả chi tiết hóa đơn
    public List<HoaDonChiTiet> getAll() {
        return chiTietRepo.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<HoaDonChiTiet> getById(int id) {
        return chiTietRepo.findById(id);
    }

    // ✅ Thêm mới
    public HoaDonChiTiet create(HoaDonChiTiet chiTiet) {
        return chiTietRepo.save(chiTiet);
    }

    // ✅ Cập nhật
    public HoaDonChiTiet update(int id, HoaDonChiTiet updated) {
        Optional<HoaDonChiTiet> existingOpt = chiTietRepo.findById(id);
        if (existingOpt.isPresent()) {
            HoaDonChiTiet existing = existingOpt.get();
            existing.setHoaDon(updated.getHoaDon());
            existing.setSanPham(updated.getSanPham());
            existing.setGiaBan(updated.getGiaBan());
            existing.setSoLuong(updated.getSoLuong());
            return chiTietRepo.save(existing);
        }
        return null;
    }

    // ✅ Xoá
    public void delete(int id) {
        chiTietRepo.deleteById(id);
    }

    // ✅ Lấy chi tiết theo hóa đơn
    public List<HoaDonChiTiet> getByHoaDon(HoaDon hoaDon) {
        return chiTietRepo.findByHoaDon(hoaDon);
    }

    // ✅ Lấy chi tiết theo sản phẩm
    public List<HoaDonChiTiet> getBySanPham(SanPham sanPham) {
        return chiTietRepo.findBySanPham(sanPham);
    }

    // ✅ Tìm chi tiết theo hóa đơn và sản phẩm
    public HoaDonChiTiet getByHoaDonAndSanPham(HoaDon hoaDon, SanPham sanPham) {
        return chiTietRepo.findByHoaDonAndSanPham(hoaDon, sanPham);
    }
}
