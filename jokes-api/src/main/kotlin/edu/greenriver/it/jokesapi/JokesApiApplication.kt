package edu.greenriver.it.jokesapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JokesApiApplication

fun main(args: Array<String>)
{
    runApplication<JokesApiApplication>(*args)
}
