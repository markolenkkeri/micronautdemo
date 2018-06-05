package com.vincit.micronautdemo

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Value
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.netty.configuration.NettyHttpServerConfiguration
import io.micronaut.runtime.server.EmbeddedServer

import javax.annotation.Nullable
import javax.inject.Inject
import javax.validation.constraints.Null


@Controller("/hello")
class HelloController {

    @Inject EmbeddedServer embeddedServer

    @Inject HelloService helloService

    @Inject HelloClient helloClient

    @Get("{/name}")
    String index(@Nullable String name) {
        return helloService.getMessage(name) + " from ${embeddedServer.port}"
    }

    @Get("/client{/name}")
    String clientIndex(@Nullable String name) {
        return helloClient.index(name)
    }
}