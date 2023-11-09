package com.librarymanagement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "ISBN", nullable = false, unique = true)
    private Integer isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "COPIES", nullable = false)
    private Integer copies;

}
