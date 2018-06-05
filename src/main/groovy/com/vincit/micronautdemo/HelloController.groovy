package com.vincit.micronautdemo

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Value
import io.micronaut.context.env.Environment
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

import javax.annotation.Nullable
import javax.inject.Inject
import javax.validation.constraints.Null


@Controller("/hello")
class HelloController {

    @Inject HelloService helloService

    @Inject HelloClient helloClient

    @Get("{/name}")
    String index(@Nullable String name) {
        return helloService.getMessage(name)
    }

    @Get("/client{/name}")
    String clientIndex(@Nullable String name) {
        return helloClient.index(name)
    }
}