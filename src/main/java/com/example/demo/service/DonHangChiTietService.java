package com.example.demo.service;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangChiTiet;
import com.example.demo.model.SanPham;
import com.example.demo.repository.DonHangChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonHangChiTietService {

    @Autowired
    private DonHangChiTietRepository chiTietRepository;

    // ✅ Lấy tất cả chi tiết
    public List<DonHangChiTiet> getAll() {
        return chiTietRepository.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<DonHangChiTiet> getById(int id) {
        return chiTietRepository.findById(id);
    }

    // ✅ Thêm mới
    public DonHangChiTiet create(DonHangChiTiet chiTiet) {
        return chiTietRepository.save(chiTiet);
    }

    // ✅ Cập nhật
    public DonHangChiTiet update(int id, DonHangChiTiet updated) {
        Optional<DonHangChiTiet> existingOpt = chiTietRepository.findById(id);
        if (existingOpt.isPresent()) {
            DonHangChiTiet existing = existingOpt.get();
            existing.setDonHang(updated.getDonHang());
            existing.setSanPham(updated.getSanPham());
            existing.setSoLuong(updated.getSoLuong());
            existing.setGiaBan(updated.getGiaBan());
            return chiTietRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá theo ID
    public void delete(int id) {
        chiTietRepository.deleteById(id);
    }

    // ✅ Xoá theo đơn hàng
    public void deleteByDonHang(DonHang donHang) {
        chiTietRepository.deleteByDonHang(donHang);
    }

    // ✅ Lấy danh sách theo đơn hàng
    public List<DonHangChiTiet> getByDonHang(DonHang donHang) {
        return chiTietRepository.findByDonHang(donHang);
    }

    // ✅ Lấy danh sách theo sản phẩm
    public List<DonHangChiTiet> getBySanPham(SanPham sanPham) {
        return chiTietRepository.findBySanPham(sanPham);
    }

    // ✅ Lấy chi tiết theo đơn hàng + sản phẩm
    public DonHangChiTiet getByDonHangAndSanPham(DonHang donHang, SanPham sanPham) {
        return chiTietRepository.findByDonHangAndSanPham(donHang, sanPham);
    }
}
