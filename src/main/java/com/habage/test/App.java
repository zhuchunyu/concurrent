package com.habage.test;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        EventBus eb = vertx.eventBus();
        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });

        vertx.createHttpServer().requestHandler(req -> {
            System.out.println(req.headers());

            eb.publish("news.uk.sport", "Yay! Someone kicked a ball");

            req.response().putHeader("content-type", "text/plain").end("Hello World!");
        }).listen(8080);
        System.out.println("listening on 8080");
    }
}
