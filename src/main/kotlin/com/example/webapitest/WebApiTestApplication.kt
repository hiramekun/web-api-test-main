package com.example.webapitest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApiTestApplication

fun main(args: Array<String>) {
    runApplication<WebApiTestApplication>(*args)
}
