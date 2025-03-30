package com.example.demo.service;

import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.SanPham;
import com.example.demo.repository.GioHangChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    // ✅ Lấy tất cả chi tiết giỏ hàng
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    // ✅ Lấy chi tiết theo ID
    public Optional<GioHangChiTiet> getById(int id) {
        return gioHangChiTietRepository.findById(id);
    }

    // ✅ Thêm mới chi tiết
    public GioHangChiTiet create(GioHangChiTiet chiTiet) {
        return gioHangChiTietRepository.save(chiTiet);
    }

    // ✅ Cập nhật chi tiết
    public GioHangChiTiet update(int id, GioHangChiTiet updated) {
        Optional<GioHangChiTiet> existingOpt = gioHangChiTietRepository.findById(id);
        if (existingOpt.isPresent()) {
            GioHangChiTiet existing = existingOpt.get();
            existing.setGioHang(updated.getGioHang());
            existing.setSanPham(updated.getSanPham());
            existing.setSoLuong(updated.getSoLuong());
            existing.setGiaBan(updated.getGiaBan());
            return gioHangChiTietRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá theo ID
    public void delete(int id) {
        gioHangChiTietRepository.deleteById(id);
    }

    // ✅ Xoá tất cả chi tiết theo giỏ hàng
    public void deleteByGioHang(GioHang gioHang) {
        gioHangChiTietRepository.deleteByGioHang(gioHang);
    }

    // ✅ Lấy danh sách chi tiết theo giỏ hàng
    public List<GioHangChiTiet> getByGioHang(GioHang gioHang) {
        return gioHangChiTietRepository.findByGioHang(gioHang);
    }

    // ✅ Lấy danh sách chi tiết theo sản phẩm
    public List<GioHangChiTiet> getBySanPham(SanPham sanPham) {
        return gioHangChiTietRepository.findBySanPham(sanPham);
    }

    // ✅ Lấy chi tiết theo giỏ hàng và sản phẩm
    public GioHangChiTiet getByGioHangAndSanPham(GioHang gioHang, SanPham sanPham) {
        return gioHangChiTietRepository.findByGioHangAndSanPham(gioHang, sanPham);
    }
}
