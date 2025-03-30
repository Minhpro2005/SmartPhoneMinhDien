package com.example.demo.service;

import com.example.demo.model.DanhGia;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.repository.DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DanhGiaService {

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    // ✅ Lấy tất cả đánh giá
    public List<DanhGia> getAll() {
        return danhGiaRepository.findAll();
    }

    // ✅ Lấy đánh giá theo ID
    public Optional<DanhGia> getById(int id) {
        return danhGiaRepository.findById(id);
    }

    // ✅ Thêm đánh giá mới
    public DanhGia create(DanhGia danhGia) {
        if (danhGia.getNgayDanhGia() == null) {
            danhGia.setNgayDanhGia(new Date());
        }
        return danhGiaRepository.save(danhGia);
    }

    // ✅ Cập nhật đánh giá
    public DanhGia update(int id, DanhGia updated) {
        Optional<DanhGia> existingOpt = danhGiaRepository.findById(id);
        if (existingOpt.isPresent()) {
            DanhGia existing = existingOpt.get();
            existing.setKhachHang(updated.getKhachHang());
            existing.setSanPham(updated.getSanPham());
            existing.setSoSao(updated.getSoSao());
            existing.setNoiDung(updated.getNoiDung());
            existing.setNgayDanhGia(updated.getNgayDanhGia());
            existing.setHinhAnh(updated.getHinhAnh());
            return danhGiaRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá đánh giá
    public void delete(int id) {
        danhGiaRepository.deleteById(id);
    }

    // ✅ Lấy theo khách hàng
    public List<DanhGia> getByKhachHang(KhachHang khachHang) {
        return danhGiaRepository.findByKhachHang(khachHang);
    }

    // ✅ Lấy theo sản phẩm
    public List<DanhGia> getBySanPham(SanPham sanPham) {
        return danhGiaRepository.findBySanPham(sanPham);
    }

    // ✅ Lấy theo số sao
    public List<DanhGia> getBySoSao(int soSao) {
        return danhGiaRepository.findBySoSao(soSao);
    }
}
