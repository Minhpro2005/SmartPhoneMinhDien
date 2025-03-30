package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    // ✅ Lấy tất cả hóa đơn
    @GetMapping
    public List<HoaDon> getAll() {
        return hoaDonService.getAll();
    }

    // ✅ Lấy hóa đơn theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<HoaDon> getById(@PathVariable int id) {
        Optional<HoaDon> hoaDon = hoaDonService.getById(id);
        return hoaDon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Tạo mới hóa đơn
    @PostMapping
    public ResponseEntity<HoaDon> create(@RequestBody HoaDon hoaDon) {
        HoaDon created = hoaDonService.create(hoaDon);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật hóa đơn
    @PutMapping("/id/{id}")
    public ResponseEntity<HoaDon> update(@PathVariable int id, @RequestBody HoaDon hoaDon) {
        HoaDon updated = hoaDonService.update(id, hoaDon);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xóa hóa đơn
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        hoaDonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm hóa đơn theo khách hàng
    @GetMapping("/khachhang/{maKH}")
    public ResponseEntity<List<HoaDon>> getByKhachHang(@PathVariable int maKH) {
        Optional<KhachHang> khachHang = khachHangService.getKhachHangById(maKH);
        return khachHang.map(kh -> ResponseEntity.ok(hoaDonService.getByKhachHang(kh)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Tìm hóa đơn theo khuyến mãi
    @GetMapping("/khuyenmai/{maKM}")
    public ResponseEntity<List<HoaDon>> getByKhuyenMai(@PathVariable int maKM) {
        Optional<KhuyenMai> km = khuyenMaiService.getById(maKM);
        return km.map(k -> ResponseEntity.ok(hoaDonService.getByKhuyenMai(k)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Tìm hóa đơn theo người tạo (LIKE)
    @GetMapping("/nguoitao")
    public List<HoaDon> searchByNguoiTao(@RequestParam String keyword) {
        return hoaDonService.searchByNguoiTao(keyword);
    }

    // ✅ Lọc hóa đơn theo trạng thái
    @GetMapping("/trangthai/{trangThai}")
    public List<HoaDon> getByTrangThai(@PathVariable boolean trangThai) {
        return hoaDonService.getByTrangThai(trangThai);
    }

    // ✅ Lọc hóa đơn theo khoảng ngày tạo
    @GetMapping("/ngaytao")
    public List<HoaDon> getByNgayTaoBetween(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return hoaDonService.getByNgayTaoBetween(from, to);
    }
}
