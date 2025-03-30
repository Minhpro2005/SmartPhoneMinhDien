package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NhaCungCap")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhaCungCap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNCC;

    @Column(nullable = false)
    private String tenNCC;

    private String sdt;

    private String diaChi;

    @Column(unique = true)
    private String email;

    private String hinhAnh;
}
