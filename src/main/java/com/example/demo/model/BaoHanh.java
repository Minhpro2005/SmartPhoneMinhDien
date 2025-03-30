package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "BaoHanh")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaoHanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBH;

    @ManyToOne
    @JoinColumn(name = "maSP", nullable = false)
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang khachHang;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date ngayMua;

    @Column(nullable = false)
    private int thoiHanBaoHanh; // tháng

    @Temporal(TemporalType.DATE)
    private Date ngayHetHan; // Tính trong Service khi thêm mới

    @Column
    private boolean trangThai = true;

    private String hinhAnh;
}
