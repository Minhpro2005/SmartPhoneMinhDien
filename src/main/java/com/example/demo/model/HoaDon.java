package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "HoaDon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHD;

    @ManyToOne
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "maKM")
    private KhuyenMai khuyenMai;

    @Column(nullable = false)
    private int tongSoLuong = 1;

    @Column(precision = 18, scale = 2)
    private BigDecimal thanhTien;

    private String hinhThucThanhToan;

    @Temporal(TemporalType.DATE)
    private Date ngayTao;

    private String nguoiTao;

    private boolean trangThai = true;
}
