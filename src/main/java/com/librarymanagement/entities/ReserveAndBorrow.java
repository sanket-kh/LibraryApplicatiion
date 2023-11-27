package com.librarymanagement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "RESERVE_AND_BURROW")
public class ReserveAndBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RESERVED")
    private Boolean reserved;

    @Column(name = "IS_ISSUED")
    private Boolean isIssued;

    @Column(name = "ISSUED_DATE")
    private LocalDate issueDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToOne(cascade = CascadeType.ALL)
    private Fine fine;

}
