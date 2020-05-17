package ru.itis.hateoas.clients;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.itis.hateoas.dto.RestaurantDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReactorRestaurantApiClientWebClientImpl implements RestaurantClient {

    private final WebClient client;
    private final JSONParser parser;
    private final List<RestaurantDto> dtos;

    public ReactorRestaurantApiClientWebClientImpl(@Value("${restaurants:get:request:url}") String url) {
        parser = new JSONParser();
        dtos = new ArrayList<>();
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl("http://localhost:80/restaurant-management/records")
                .build();
    }

    @Override
    public Mono<String> getAll() {
        return client.get()
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
    }

    @SneakyThrows
    private Mono<List<RestaurantDto>> mapper(String str){
        List<String> list = Arrays.asList(str.split("\n"));
        System.out.println(list.get(0).substring(5));
        for (String line : list){
            String shortStr = line.substring(5);
            JSONObject json = (JSONObject) parser.parse(shortStr);
            dtos.add(RestaurantDto.builder()
                    .name((String) json.get("name"))
                    .description((String) json.get("address)"))
                    .build());
        }
        System.err.println(dtos);
        return Mono.just(dtos);
    }
}
