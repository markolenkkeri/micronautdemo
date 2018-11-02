package com.vincit.micronautdemo

import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.annotation.Client

import javax.annotation.Nullable

@Client("micronautdemo")
interface HelloClient {

    @Get("/hello{/name}")
    String index(@Nullable String name)
}