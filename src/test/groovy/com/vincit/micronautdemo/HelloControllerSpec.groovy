package com.vincit.micronautdemo

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HelloControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())
    static String TESTSTRING = "somename"

    @Unroll
    void "Test index with username #username and url postfix #urlpostfix"(String username, String urlpostfix, String response) {
        expect:
        client.toBlocking().retrieve("/hello${urlpostfix}").matches(response)

        where:
        username        | urlpostfix            || response
        ""              | ""                    || /Hello, stranger! from .*/
        "erkki"         | "/erkki"              || /Hello, erkki! from .*/
        "kaksi osainen" | "/kaksi%20osainen"    || /Hello, kaksi osainen! from.*/
    }

    void "Controller index invocation invokes method in service" () {
        given:"A controller with a mocked service and embedded server"
        HelloController helloController = embeddedServer.applicationContext.getBean(HelloController)
        helloController.helloService = Mock(HelloService)
        helloController.embeddedServer = Mock(EmbeddedServer)

        when:"A request is made"
        helloController.index(TESTSTRING)

        then:"The service is called"
        1 * helloController.helloService.getMessage(TESTSTRING)

    }

    void "Controller client api invocation invokes method in service and in client" () {
        given:"A controller with a mocked client"
        HelloController helloController = new HelloController()
        helloController.helloClient = Mock(HelloClient)

        when:"A request is made"
        helloController.clientIndex(TESTSTRING)

        then:"The client is called"
        1 * helloController.helloClient.index(TESTSTRING)
    }
}
