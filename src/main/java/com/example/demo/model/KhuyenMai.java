package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "KhuyenMai")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKM;

    @Column(nullable = false)
    private String tenKM;   

    @Column(precision = 5, scale = 2) //xác định độ chính xác của các trường số kiểu BigDecimal hoặc double
    private BigDecimal giamGia;


    @Temporal(TemporalType.DATE) //temporyal de map kieu ngay
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
}
