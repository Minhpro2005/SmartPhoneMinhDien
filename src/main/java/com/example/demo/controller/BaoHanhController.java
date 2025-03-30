package com.example.demo.controller;

import com.example.demo.model.BaoHanh;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.service.BaoHanhService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/baohanh")
public class BaoHanhController {

    @Autowired
    private BaoHanhService baoHanhService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả bảo hành
    @GetMapping
    public List<BaoHanh> getAll() {
        return baoHanhService.getAll();
    }

    // ✅ Lấy theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<BaoHanh> getById(@PathVariable int id) {
        Optional<BaoHanh> bh = baoHanhService.getById(id);
        return bh.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm mới bảo hành
    @PostMapping
    public ResponseEntity<BaoHanh> create(@RequestBody BaoHanh baoHanh) {
        BaoHanh created = baoHanhService.create(baoHanh);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật bảo hành
    @PutMapping("/id/{id}")
    public ResponseEntity<BaoHanh> update(@PathVariable int id, @RequestBody BaoHanh baoHanh) {
        BaoHanh updated = baoHanhService.update(id, baoHanh);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xoá bảo hành theo ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        baoHanhService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy bảo hành theo khách hàng
    @GetMapping("/khachhang/{maKH}")
    public ResponseEntity<List<BaoHanh>> getByKhachHang(@PathVariable int maKH) {
        Optional<KhachHang> kh = khachHangService.getKhachHangById(maKH);
        return kh.map(value -> ResponseEntity.ok(baoHanhService.getByKhachHang(value)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy bảo hành theo sản phẩm
    @GetMapping("/sanpham/{maSP}")
    public ResponseEntity<List<BaoHanh>> getBySanPham(@PathVariable int maSP) {
        Optional<SanPham> sp = sanPhamService.getById(maSP);
        return sp.map(value -> ResponseEntity.ok(baoHanhService.getBySanPham(value)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy theo trạng thái (1 = còn bảo hành, 0 = đã hết, huỷ...)
    @GetMapping("/trangthai/{trangThai}")
    public List<BaoHanh> getByTrangThai(@PathVariable boolean trangThai) {
        return baoHanhService.getByTrangThai(trangThai);
    }
}
