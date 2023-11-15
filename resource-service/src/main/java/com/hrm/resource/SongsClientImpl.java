package com.hrm.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongsClientImpl implements SongsClient {

    private final WebClient webClient;

    @Value("${clients.song-service}")
    private String songServiceUri;

    @Override
    public void ping() {
        log.info("song-service pinged");
        String response = webClient.get().uri(songServiceUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Received response: {}", response);
    }
}
