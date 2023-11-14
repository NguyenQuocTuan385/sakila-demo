package com.group06.sakila.services;

import com.group06.sakila.entities.Film;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class ServerBServiceImpl implements ServerBService {
    private final WebClient webClient;
    @Value("${serverB.secretKey}")
    private String secretKey;

    public ServerBServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api").build();

    }

    @Override
    public List<Film> getAllFilms() {
        String url = "/filmsB";
        String timestamp = new Date(System.currentTimeMillis()).toString();
        String token = hashToken(url, timestamp);

        // Sử dụng WebClient để thực hiện một GET request

        Flux<Film> filmFlux = webClient.get()
                .uri(url)
                .header("Authorization","Bearer " + token)  // Thêm token vào header
                .header("Timestamp", timestamp)
                .header("UrlApi", url)
                .retrieve()
                .bodyToFlux(Film.class);


        // Chuyển đổi Flux thành List (collectList trả về một Mono<List<Film>>)
        Mono<List<Film>> filmListMono = filmFlux.collectList();
        // Chờ đợi kết quả và trả về
        return filmListMono.block();
    }

    @Override
    public Film getFilmById(Integer id) {
        String url = String.format("/filmsB/%d", id);
        String timestamp = new Date(System.currentTimeMillis()).toString();
        String token = hashToken(url, timestamp);

        Mono<Film> filmMono = webClient.get()
                .uri(url)
                .header("Authorization","Bearer " + token)  // Thêm token vào header
                .header("Timestamp", timestamp)
                .header("UrlApi", url)
                .retrieve()
                .bodyToMono(Film.class);

        return filmMono.block();
    }

    public String hashToken(String url, String timestamp){
        // hash token's payload + secret key
        return DigestUtils.sha256Hex(url + timestamp + secretKey);
    }

}
