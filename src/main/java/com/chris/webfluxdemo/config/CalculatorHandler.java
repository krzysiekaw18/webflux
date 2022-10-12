package com.chris.webfluxdemo.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {

    // creating multiple handlers intetionally
    // calculator/{a}/{b}
    public Mono<ServerResponse> additionalHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a + b));
    }

    public Mono<ServerResponse> subtractionHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a - b));
    }

    public Mono<ServerResponse> multiplicationHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a * b));
    }

    public Mono<ServerResponse> divisionHandler(ServerRequest request) {
        return process(request, (a, b) -> b != 0
                ? ServerResponse.ok().bodyValue(a / b)
                : ServerResponse.badRequest().bodyValue("No divide by 0!!!"));
    }

    private Mono<ServerResponse> process(ServerRequest request,
                                         BiFunction<Integer, Integer, Mono<ServerResponse>> operationLogic) {
        int a = getValue(request, "a");
        int b = getValue(request, "b");
        return operationLogic.apply(a, b);
    }

    private int getValue(ServerRequest request, String key) {
        return Integer.parseInt(request.pathVariable(key));
    }
}
