package com.hrm.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.time.Duration;

@Configuration
public class ContextConfig {

    @Value("${clients.s3.url}")
    private String s3ClientUrl;

    @Value("${clients.s3.access_key}")
    private String s3AccessKey;

    @Value("${clients.s3.secret_key}")
    private String s3SecretKey;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.EU_CENTRAL_1)
                .endpointOverride(URI.create(s3ClientUrl))
                .forcePathStyle(true)
                .httpClientBuilder(
                        ApacheHttpClient.builder()
                                .maxConnections(100)
                                .connectionTimeout(Duration.ofSeconds(5))
                )
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(s3AccessKey, s3SecretKey)
                        )
                )
                .build();
    }
}
