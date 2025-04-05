package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.GioHangChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private BienTheSanPhamService bienTheSanPhamService;

    // Lấy tất cả chi tiết giỏ hàng
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    // Lấy chi tiết theo ID
    public Optional<GioHangChiTiet> getById(int id) {
        return gioHangChiTietRepository.findById(id);
    }

    // Thêm mới chi tiết giỏ hàng (có thể có biến thể)
    public GioHangChiTiet create(GioHangChiTiet chiTiet) {
        if (chiTiet.getBienThe() != null && chiTiet.getBienThe().getMaBienThe() > 0) {
            BienTheSanPham bienThe = bienTheSanPhamService.getById(chiTiet.getBienThe().getMaBienThe()).orElse(null);
            chiTiet.setBienThe(bienThe);
        }
        return gioHangChiTietRepository.save(chiTiet);
    }

    // Cập nhật chi tiết
    public GioHangChiTiet update(int id, GioHangChiTiet updated) {
        Optional<GioHangChiTiet> existingOpt = gioHangChiTietRepository.findById(id);
        if (existingOpt.isPresent()) {
            GioHangChiTiet existing = existingOpt.get();
            existing.setGioHang(updated.getGioHang());
            existing.setSanPham(updated.getSanPham());
            existing.setSoLuong(updated.getSoLuong());
            existing.setGiaBan(updated.getGiaBan());

            if (updated.getBienThe() != null && updated.getBienThe().getMaBienThe() > 0) {
                BienTheSanPham bienThe = bienTheSanPhamService.getById(updated.getBienThe().getMaBienThe()).orElse(null);
                existing.setBienThe(bienThe);
            } else {
                existing.setBienThe(null);
            }

            return gioHangChiTietRepository.save(existing);
        }
        return null;
    }

    // Xoá chi tiết theo ID
    public void delete(int id) {
        gioHangChiTietRepository.deleteById(id);
    }

    // Xoá tất cả chi tiết theo giỏ hàng
    public void deleteByGioHang(GioHang gioHang) {
        gioHangChiTietRepository.deleteByGioHang(gioHang);
    }

    // Lấy danh sách chi tiết theo giỏ hàng
    public List<GioHangChiTiet> getByGioHang(GioHang gioHang) {
        return gioHangChiTietRepository.findByGioHang(gioHang);
    }

    // Lấy danh sách chi tiết theo sản phẩm
    public List<GioHangChiTiet> getBySanPham(SanPham sanPham) {
        return gioHangChiTietRepository.findBySanPham(sanPham);
    }

    // Lấy chi tiết theo giỏ hàng và sản phẩm
    public GioHangChiTiet getByGioHangAndSanPham(GioHang gioHang, SanPham sanPham) {
        return gioHangChiTietRepository.findByGioHangAndSanPham(gioHang, sanPham);
    }
} 
