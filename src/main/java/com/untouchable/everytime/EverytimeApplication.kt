package com.untouchable.everytime

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EverytimeApplication

fun main(args: Array<String>) {
    runApplication<EverytimeApplication>(*args)
}
