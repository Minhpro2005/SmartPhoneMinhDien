package com.example.demo.controller;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import com.example.demo.service.GioHangService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/giohang")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private KhachHangService khachHangService;

    // ✅ Lấy tất cả giỏ hàng
    @GetMapping
    public List<GioHang> getAll() {
        return gioHangService.getAll();
    }

    // ✅ Lấy giỏ hàng theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<GioHang> getById(@PathVariable int id) {
        Optional<GioHang> gioHang = gioHangService.getById(id);
        return gioHang.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm mới giỏ hàng
    @PostMapping
    public ResponseEntity<GioHang> create(@RequestBody GioHang gioHang) {
        GioHang created = gioHangService.create(gioHang);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật giỏ hàng
    @PutMapping("/id/{id}")
    public ResponseEntity<GioHang> update(@PathVariable int id, @RequestBody GioHang gioHang) {
        GioHang updated = gioHangService.update(id, gioHang);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xóa giỏ hàng
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        gioHangService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy giỏ hàng theo mã khách hàng
    @GetMapping("/khachhang/{maKH}")
    public ResponseEntity<List<GioHang>> getByKhachHang(@PathVariable int maKH) {
        Optional<KhachHang> khachHang = khachHangService.getKhachHangById(maKH);
        return khachHang.map(kh -> ResponseEntity.ok(gioHangService.getByKhachHang(kh)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy giỏ hàng theo mã khách hàng và trạng thái
    @GetMapping("/khachhang/{maKH}/trangthai/{trangThai}")
    public ResponseEntity<List<GioHang>> getByKhachHangAndTrangThai(@PathVariable int maKH,
                                                                     @PathVariable boolean trangThai) {
        Optional<KhachHang> khachHang = khachHangService.getKhachHangById(maKH);
        return khachHang.map(kh -> ResponseEntity.ok(gioHangService.getByKhachHangAndTrangThai(kh, trangThai)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lọc theo trạng thái
    @GetMapping("/trangthai/{trangThai}")
    public List<GioHang> getByTrangThai(@PathVariable boolean trangThai) {
        return gioHangService.getByTrangThai(trangThai);
    }
}
