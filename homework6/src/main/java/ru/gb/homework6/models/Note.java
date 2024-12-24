package ru.gb.homework6.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.homework6.auxiliaries.NoteStatus;

import java.time.LocalDate;



@Getter
@Setter
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate createDate;

    @Column()
    @Enumerated(EnumType.ORDINAL)
    private NoteStatus status;

}
