package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "SanPham")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSP;

    @Column(nullable = false)
    private String tenSP;

    private String hang;

    private String mauSac;

    private String moTa;

    @Column(nullable = false)
    private int soLuong;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal giaBan;

    private String hinhAnhSP;

    private boolean trangThai = true;

}
