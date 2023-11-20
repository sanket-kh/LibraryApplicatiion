package com.librarymanagement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "FINE")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "OVER_DUE")
    private Integer overDue;

    @Column(name = "AMOUNT")
    private Integer amount;
}
