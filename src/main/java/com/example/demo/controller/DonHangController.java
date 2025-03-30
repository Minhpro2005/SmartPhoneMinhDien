package com.example.demo.controller;

import com.example.demo.model.DonHang;
import com.example.demo.model.KhachHang;
import com.example.demo.service.DonHangService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/donhang")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private KhachHangService khachHangService;

    // ✅ Lấy tất cả đơn hàng
    @GetMapping
    public List<DonHang> getAll() {
        return donHangService.getAll();
    }

    // ✅ Lấy đơn hàng theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<DonHang> getById(@PathVariable int id) {
        Optional<DonHang> dh = donHangService.getById(id);
        return dh.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm đơn hàng
    @PostMapping
    public ResponseEntity<DonHang> create(@RequestBody DonHang donHang) {
        DonHang created = donHangService.create(donHang);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật đơn hàng
    @PutMapping("/id/{id}")
    public ResponseEntity<DonHang> update(@PathVariable int id, @RequestBody DonHang donHang) {
        DonHang updated = donHangService.update(id, donHang);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xoá đơn hàng
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        donHangService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy đơn hàng theo mã khách hàng
    @GetMapping("/khachhang/{maKH}")
    public ResponseEntity<List<DonHang>> getByKhachHang(@PathVariable int maKH) {
        Optional<KhachHang> kh = khachHangService.getKhachHangById(maKH);
        return kh.map(value -> ResponseEntity.ok(donHangService.getByKhachHang(value)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy đơn hàng theo trạng thái
    @GetMapping("/trangthai/{trangThai}")
    public List<DonHang> getByTrangThai(@PathVariable String trangThai) {
        return donHangService.getByTrangThai(trangThai);
    }

    // ✅ Lọc đơn hàng theo khách hàng và trạng thái
    @GetMapping("/khachhang/{maKH}/trangthai/{trangThai}")
    public ResponseEntity<List<DonHang>> getByKhachHangAndTrangThai(@PathVariable int maKH,
                                                                     @PathVariable String trangThai) {
        Optional<KhachHang> kh = khachHangService.getKhachHangById(maKH);
        return kh.map(value -> ResponseEntity.ok(donHangService.getByKhachHangAndTrangThai(value, trangThai)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
