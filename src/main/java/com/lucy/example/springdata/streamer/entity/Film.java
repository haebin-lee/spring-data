package com.lucy.example.springdata.streamer.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "film", schema = "sakila")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "samllint(5)")
    private Integer filmId;

    @Column(name = "title", columnDefinition = "varchar(255)")
    private String title;

    @Column(name = "rating")
    private String rating;
}
