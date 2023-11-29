package com.librarymanagement.models.dtos;

import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class WebClientRequestBuiler {
    private HttpServerRequest httpRequest;
    private HttpServerResponse httpResponse;
    private String url;
}
