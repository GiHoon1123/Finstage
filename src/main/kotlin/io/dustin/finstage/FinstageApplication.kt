package io.dustin.finstage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinstageApplication

fun main(args: Array<String>) {
    runApplication<FinstageApplication>(*args)
}
