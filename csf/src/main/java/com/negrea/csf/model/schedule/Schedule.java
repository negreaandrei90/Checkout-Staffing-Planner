package com.negrea.csf.model.schedule;

import com.negrea.csf.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_date", nullable = false)
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
