package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BienTheSanPham")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BienTheSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBienThe;

    @ManyToOne
    @JoinColumn(name = "maSP", nullable = false)
    private SanPham sanPham;

    @Column(nullable = false)
    private String tenBienThe;

    @Column(nullable = false)
    private int soLuong;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal giaBan;

    private boolean trangThai = true;

    private String hinhAnh;
}
