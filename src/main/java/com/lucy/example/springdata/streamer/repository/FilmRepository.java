package com.lucy.example.springdata.streamer.repository;

import com.lucy.example.springdata.streamer.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findAllByTitleStartingWith(String title);
}
