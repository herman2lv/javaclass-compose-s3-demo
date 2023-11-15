package com.hrm.resource;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongsClientImpl implements SongsClient {

    private final WebClient webClient;
    private final EurekaClient eurekaClient;
    private final LoadBalancerClient loadBalancerClient;

    @Value("${clients.song-service}")
    private String songServiceId;

    @Override
    public void ping() {
        eurekaClient.getApplication("SONG-SERVICE")
                .getInstances().forEach(ii -> {
                    log.info("EUREKA INFO: AppName {}", ii.getAppName());
                    log.info("EUREKA INFO: Id {}", ii.getId());
                    log.info("EUREKA INFO: HostName {}", ii.getHostName());
                    log.info("EUREKA INFO: Port {}", ii.getPort());
                    log.info("EUREKA INFO: IPAddr {}", ii.getIPAddr());
                    log.info("EUREKA INFO: VIPAddress {}", ii.getVIPAddress());
                    log.info("EUREKA INFO: Status {}", ii.getStatus());
                    log.info("EUREKA INFO: Status {}", ii.getStatus());
                    log.info("EUREKA INFO: HomePageUrl {}\n", ii.getHomePageUrl());
                });

        ServiceInstance choose = loadBalancerClient.choose(songServiceId);
        log.info("CHOSEN: getServiceId {}", choose.getServiceId());
        log.info("CHOSEN: getInstanceId {}", choose.getInstanceId());
        log.info("CHOSEN: getScheme {}", choose.getScheme());
        log.info("CHOSEN: getHost {}", choose.getHost());
        log.info("CHOSEN: getPort {}", choose.getPort());
        log.info("CHOSEN: getUri {}", choose.getUri());
        log.info("CHOSEN: getMetadata {}\n", choose.getMetadata());

        log.info("song-service pinged");
        String response = webClient.get().uri(choose.getUri())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Received response: {}", response);
    }
}
