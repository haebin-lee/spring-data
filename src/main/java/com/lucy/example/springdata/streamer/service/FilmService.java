package com.lucy.example.springdata.streamer.service;

import com.lucy.example.springdata.streamer.entity.Film;
import com.lucy.example.springdata.streamer.entity.Film$;
import com.lucy.example.springdata.streamer.repository.FilmRepository;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final JPAStreamer jpaStreamer;
    private final FilmRepository filmRepository;

    @Transactional(readOnly = true)
    public List<Film> findAll() {
        return jpaStreamer.stream(Film.class).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Film> findAllStartWithUsingStreamer(String startWith) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.title.startsWith(startWith))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Film> findAllStartWithUsingJavaStream(String startWith) {
        return filmRepository.findAll().stream()
                .filter(film -> film.getTitle().startsWith(startWith))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Film> findAllStartWithUsingRepository(String startWith) {
        return filmRepository.findAllByTitleStartingWith(startWith);
    }

    public Map<String, List<Film>> findGroupByRatingUsingStreamer() {
        return jpaStreamer.stream(Film.class)
                .collect(Collectors.groupingBy(Film$.rating));
    }
}
