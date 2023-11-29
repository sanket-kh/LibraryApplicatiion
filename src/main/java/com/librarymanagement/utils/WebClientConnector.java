package com.librarymanagement.utils;


import com.librarymanagement.models.dtos.WebClientRequestBuiler;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Log4j2
public class WebClientConnector {
    public static String callPost(W webClientRequest)
}
