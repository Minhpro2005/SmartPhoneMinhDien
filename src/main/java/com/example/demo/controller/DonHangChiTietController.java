package com.example.demo.controller;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangChiTiet;
import com.example.demo.model.SanPham;
import com.example.demo.service.DonHangChiTietService;
import com.example.demo.service.DonHangService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/donhangchitiet")
public class DonHangChiTietController {

    @Autowired
    private DonHangChiTietService chiTietService;

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả chi tiết đơn hàng
    @GetMapping
    public List<DonHangChiTiet> getAll() {
        return chiTietService.getAll();
    }

    // ✅ Lấy chi tiết theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<DonHangChiTiet> getById(@PathVariable int id) {
        Optional<DonHangChiTiet> ct = chiTietService.getById(id);
        return ct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm mới chi tiết
    @PostMapping
    public ResponseEntity<DonHangChiTiet> create(@RequestBody DonHangChiTiet chiTiet) {
        DonHangChiTiet created = chiTietService.create(chiTiet);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật chi tiết
    @PutMapping("/id/{id}")
    public ResponseEntity<DonHangChiTiet> update(@PathVariable int id, @RequestBody DonHangChiTiet chiTiet) {
        DonHangChiTiet updated = chiTietService.update(id, chiTiet);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xoá theo ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        chiTietService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Xoá theo đơn hàng
    @DeleteMapping("/donhang/{maDonHang}")
    public ResponseEntity<Void> deleteByDonHang(@PathVariable int maDonHang) {
        Optional<DonHang> donHang = donHangService.getById(maDonHang);
        if (donHang.isPresent()) {
            chiTietService.deleteByDonHang(donHang.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Lấy chi tiết theo đơn hàng
    @GetMapping("/donhang/{maDonHang}")
    public ResponseEntity<List<DonHangChiTiet>> getByDonHang(@PathVariable int maDonHang) {
        Optional<DonHang> donHang = donHangService.getById(maDonHang);
        return donHang.map(dh -> ResponseEntity.ok(chiTietService.getByDonHang(dh)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy chi tiết theo sản phẩm
    @GetMapping("/sanpham/{maSP}")
    public ResponseEntity<List<DonHangChiTiet>> getBySanPham(@PathVariable int maSP) {
        Optional<SanPham> sp = sanPhamService.getById(maSP);
        return sp.map(s -> ResponseEntity.ok(chiTietService.getBySanPham(s)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy theo đơn hàng và sản phẩm
    @GetMapping("/donhang/{maDonHang}/sanpham/{maSP}")
    public ResponseEntity<DonHangChiTiet> getByDonHangAndSanPham(@PathVariable int maDonHang,
                                                                  @PathVariable int maSP) {
        Optional<DonHang> donHang = donHangService.getById(maDonHang);
        Optional<SanPham> sanPham = sanPhamService.getById(maSP);
        if (donHang.isPresent() && sanPham.isPresent()) {
            DonHangChiTiet ct = chiTietService.getByDonHangAndSanPham(donHang.get(), sanPham.get());
            if (ct != null) return ResponseEntity.ok(ct);
        }
        return ResponseEntity.notFound().build();
    }
}
