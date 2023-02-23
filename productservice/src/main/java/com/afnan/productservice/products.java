package com.afnan.productservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tblproducts")
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;


    private String pname;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String pimg;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String pshort;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String plong;
    private long pprice;
}
