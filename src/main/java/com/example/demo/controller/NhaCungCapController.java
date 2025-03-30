package com.example.demo.controller;

import com.example.demo.model.NhaCungCap;
import com.example.demo.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/nhacungcap") // Gốc: /smartphone/nhacungcap
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nccService;

    // ✅ Lấy danh sách tất cả nhà cung cấp
    @GetMapping
    public List<NhaCungCap> getAll() {
        return nccService.getAll();
    }

    // ✅ Lấy nhà cung cấp theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<NhaCungCap> getById(@PathVariable int id) {
        Optional<NhaCungCap> ncc = nccService.getById(id);
        return ncc.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm mới nhà cung cấp
    @PostMapping
    public ResponseEntity<NhaCungCap> create(@RequestBody NhaCungCap ncc) {
        NhaCungCap created = nccService.create(ncc);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật nhà cung cấp
    @PutMapping("/id/{id}")
    public ResponseEntity<NhaCungCap> update(@PathVariable int id, @RequestBody NhaCungCap ncc) {
        NhaCungCap updated = nccService.update(id, ncc);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá nhà cung cấp
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        nccService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm kiếm theo tên nhà cung cấp
    @GetMapping("/search")
    public List<NhaCungCap> searchByName(@RequestParam("keyword") String keyword) {
        return nccService.searchByTen(keyword);
    }

    // ✅ Tìm theo SĐT
    @GetMapping("/sdt/{sdt}")
    public List<NhaCungCap> searchBySdt(@PathVariable String sdt) {
        return nccService.searchBySdt(sdt);
    }

    // ✅ Tìm theo Email
    @GetMapping("/email/{email}")
    public ResponseEntity<NhaCungCap> searchByEmail(@PathVariable String email) {
        NhaCungCap ncc = nccService.searchByEmail(email);
        return ncc != null ? ResponseEntity.ok(ncc) : ResponseEntity.notFound().build();
    }
}
