package com.lucy.example.springdata.streamer.service

import com.lucy.example.springdata.streamer.entity.Film
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FilmServiceTest extends Specification {

    @Autowired
    FilmService filmService;

    def "FindAll"() {
        when:
        List<Film> films = filmService.findAll();

        then:
        films.size() == 1000;
        films.get(0).title == 'ACADEMY DINOSAUR';

    }

    def "FindAllStartWithUsingStreamer"() {
        when:
        List<Film> films = filmService.findAllStartWithUsingStreamer("A");

        then:
        films.size() == 46;
        // 54ms
    }

    def "FindAllStartWithUsingJavaStream"() {
        when:
        List<Film> films = filmService.findAllStartWithUsingJavaStream("A");

        then:
        films.size() == 46;
        // 45ms

    }

    def "FindAllStartWithUsingRepository"() {
        when:
        List<Film> films = filmService.findAllStartWithUsingRepository("A");

        then:
        films.size() == 46;
        // 27ms

    }

    def "FindGroupByRatingUsingStreamer"() {
        when:
        Map<String, List<Film>> map = filmService.findGroupByRatingUsingStreamer();
        map.entrySet().forEach({ m ->
            System.out.println(m.key + ":  " + m.getValue().stream().map({ f -> f.getTitle() }).collect(Collectors.toList()));
        })

        then:
        map.size() == 5;
    }
}
