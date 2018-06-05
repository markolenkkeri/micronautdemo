package com.vincit.micronautdemo

import io.micronaut.http.client.Client
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

import javax.annotation.Nullable

@Client("micronautdemo")
interface HelloClient {

    @Get("/hello{/name}")
    String index(@Nullable String name)
}