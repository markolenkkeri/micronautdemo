package com.vincit.micronautdemo

import javax.inject.Singleton

@Singleton
class HelloService {

    String getMessage(String name)
    {
        if(name)return "Hello, $name!"
        return "Hello, stranger!"
    }
}