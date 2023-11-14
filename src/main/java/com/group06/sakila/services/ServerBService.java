package com.group06.sakila.services;

import com.group06.sakila.entities.Film;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ServerBService {
    private final WebClient webClient;
    public ServerBService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api").build();

    }

    public List<Film> getAllFilms() throws Exception {
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

    public Film getFilmById(Integer id) throws Exception {
        // Sử dụng WebClient để thực hiện một GET request

        Mono<Film> filmMono = webClient.get()
                .uri("/filmsB/{id}", id)
                .retrieve()
                .bodyToMono(Film.class);

        return filmMono.block();
    }

}
