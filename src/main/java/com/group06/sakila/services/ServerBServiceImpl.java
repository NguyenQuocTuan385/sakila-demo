package com.group06.sakila.services;

import com.group06.sakila.entities.Film;
import com.group06.sakila.request_models.FilmRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ServerBServiceImpl implements ServerBService{
    private final WebClient webClient;
    public ServerBServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api").build();

    }

    public List<Film> getAllFilms() {
        // Sử dụng WebClient để thực hiện một GET request

        Flux<Film> filmFlux = webClient.get()
                .uri("/filmsB")
                .retrieve()
                .bodyToFlux(Film.class);


        // Chuyển đổi Flux thành List (collectList trả về một Mono<List<Film>>)
        Mono<List<Film>> filmListMono = filmFlux.collectList();
        // Chờ đợi kết quả và trả về
        return filmListMono.block();
    }

    public Film getFilmById(Integer id) {
        // Sử dụng WebClient để thực hiện một GET request

        Mono<Film> filmMono = webClient.get()
                .uri("/filmsB/{id}", id)
                .retrieve()
                .bodyToMono(Film.class);

        return filmMono.block();
    }

    @Override
    public Film createFilm(FilmRequest filmRequest) {
        Mono<Film> filmMono = webClient.post()
                .uri("/filmsB")
                .body(Mono.just(filmRequest), FilmRequest.class)
                .retrieve()
                .bodyToMono(Film.class);

        return filmMono.block();
    }

}
