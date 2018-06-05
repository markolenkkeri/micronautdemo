package com.vincit.micronautdemo;

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HelloControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    @Unroll
    void "test index with username #username and url postfix #urlpostfix"(String username, String urlpostfix, String response) {
        expect:
        response == client.toBlocking().retrieve("/hello${urlpostfix}")

        where:
        username | urlpostfix || response
        ""       | ""         || "Hello, stranger!"
        "erkki"  | "/erkki"   || "Hello, erkki!"
    }
}
