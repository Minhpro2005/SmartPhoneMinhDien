package com.example.demo.service;

import com.example.demo.model.BaoHanh;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.repository.BaoHanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaoHanhService {

    @Autowired
    private BaoHanhRepository baoHanhRepository;

    // ✅ Lấy tất cả bản ghi bảo hành
    public List<BaoHanh> getAll() {
        return baoHanhRepository.findAll();
    }

    // ✅ Lấy theo ID
    public Optional<BaoHanh> getById(int id) {
        return baoHanhRepository.findById(id);
    }

    // ✅ Thêm mới bảo hành (có tính ngày hết hạn)
    public BaoHanh create(BaoHanh baoHanh) {
        baoHanh.setNgayHetHan(tinhNgayHetHan(baoHanh.getNgayMua(), baoHanh.getThoiHanBaoHanh()));
        return baoHanhRepository.save(baoHanh);
    }

    // ✅ Cập nhật bảo hành
    public BaoHanh update(int id, BaoHanh updated) {
        Optional<BaoHanh> existingOpt = baoHanhRepository.findById(id);
        if (existingOpt.isPresent()) {
            BaoHanh existing = existingOpt.get();
            existing.setSanPham(updated.getSanPham());
            existing.setKhachHang(updated.getKhachHang());
            existing.setNgayMua(updated.getNgayMua());
            existing.setThoiHanBaoHanh(updated.getThoiHanBaoHanh());
            existing.setTrangThai(updated.isTrangThai());
            existing.setHinhAnh(updated.getHinhAnh());
            existing.setNgayHetHan(tinhNgayHetHan(updated.getNgayMua(), updated.getThoiHanBaoHanh()));
            return baoHanhRepository.save(existing);
        }
        return null;
    }

    // ✅ Xoá
    public void delete(int id) {
        baoHanhRepository.deleteById(id);
    }

    // ✅ Lấy theo khách hàng
    public List<BaoHanh> getByKhachHang(KhachHang khachHang) {
        return baoHanhRepository.findByKhachHang(khachHang);
    }

    // ✅ Lấy theo sản phẩm
    public List<BaoHanh> getBySanPham(SanPham sanPham) {
        return baoHanhRepository.findBySanPham(sanPham);
    }

    // ✅ Lấy theo trạng thái
    public List<BaoHanh> getByTrangThai(boolean trangThai) {
        return baoHanhRepository.findByTrangThai(trangThai);
    }

    // ✅ Tính ngày hết hạn bảo hành từ ngày mua + thời hạn (tháng)
    private Date tinhNgayHetHan(Date ngayMua, int soThang) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayMua);
        calendar.add(Calendar.MONTH, soThang);
        return calendar.getTime();
    }
}
