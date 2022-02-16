package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.internal.build.AllowPrintStacktrace;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@DynamicUpdate
@Table(name = "Cake", uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "title")})
@AllArgsConstructor
@NoArgsConstructor
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "description", unique = true, nullable = false, length = 100)
    private String description;

    @Column(name = "image", unique = true, nullable = false, length = 300)
    private String image;

}
