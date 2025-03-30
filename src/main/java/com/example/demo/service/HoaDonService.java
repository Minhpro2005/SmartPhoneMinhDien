package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;
import com.example.demo.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    // ✅ Lấy tất cả hóa đơn
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<HoaDon> getById(int id) {
        return hoaDonRepository.findById(id);
    }

    // ✅ Thêm hóa đơn
    public HoaDon create(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    // ✅ Cập nhật hóa đơn
    public HoaDon update(int id, HoaDon updated) {
        Optional<HoaDon> existingOpt = hoaDonRepository.findById(id);
        if (existingOpt.isPresent()) {
            HoaDon existing = existingOpt.get();
            existing.setKhachHang(updated.getKhachHang());
            existing.setKhuyenMai(updated.getKhuyenMai());
            existing.setTongSoLuong(updated.getTongSoLuong());
            existing.setThanhTien(updated.getThanhTien());
            existing.setHinhThucThanhToan(updated.getHinhThucThanhToan());
            existing.setNgayTao(updated.getNgayTao());
            existing.setNguoiTao(updated.getNguoiTao());
            existing.setTrangThai(updated.isTrangThai());
            return hoaDonRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá hóa đơn
    public void delete(int id) {
        hoaDonRepository.deleteById(id);
    }

    // ✅ Tìm theo khách hàng
    public List<HoaDon> getByKhachHang(KhachHang khachHang) {
        return hoaDonRepository.findByKhachHang(khachHang);
    }

    // ✅ Tìm theo khuyến mãi
    public List<HoaDon> getByKhuyenMai(KhuyenMai khuyenMai) {
        return hoaDonRepository.findByKhuyenMai(khuyenMai);
    }

    // ✅ Tìm theo người tạo
    public List<HoaDon> searchByNguoiTao(String keyword) {
        return hoaDonRepository.findByNguoiTaoContainingIgnoreCase(keyword);
    }

    // ✅ Lọc theo trạng thái
    public List<HoaDon> getByTrangThai(boolean trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    // ✅ Lọc theo ngày tạo
    public List<HoaDon> getByNgayTaoBetween(Date start, Date end) {
        return hoaDonRepository.findByNgayTaoBetween(start, end);
    }
}
