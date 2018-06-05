package com.vincit.micronautdemo

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

import javax.annotation.Nullable
import javax.inject.Inject


@Controller("/hello")
class HelloController {

    @Inject HelloService helloService

    @Get("{/name}")
    String index(@Nullable String name) {
        return helloService.getMessage(name)
    }
}