package edu.greenriver.it.jokesapi.rest

import edu.greenriver.it.jokesapi.model.Joke
import edu.greenriver.it.jokesapi.repositories.IJokeRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
internal class JokeRestController(private val repository: IJokeRepository)
{
    @GetMapping("/jokes")
    fun allJokes(): MutableIterable<Joke>
    {
        return repository.findAll()
    }

    @PostMapping("/jokes", consumes = ["application/json; charset=utf8"], produces = ["application/json; charset=utf8"])
    fun newJoke(@RequestBody newJoke: Joke): Joke
    {
        return repository.save(newJoke)
    }

    @GetMapping("/jokes/{id}")
    fun oneJoke(@PathVariable id: Long): Optional<Joke>
    {
        return repository.findById(id)
    }

    @PutMapping("/jokes/{id}")
    fun updateJoke(@RequestBody newJoke: Joke, @PathVariable id: Long): Joke
    {
        return repository.findById(id)
                .map { joke ->
                    joke.type = newJoke.type
                    joke.setup = newJoke.setup
                    joke.punchline = newJoke.punchline

                    repository.save(joke)
                }
                .orElseGet {
                    newJoke.id = id
                    repository.save(newJoke)
                }
    }

    @DeleteMapping("/jokes/{id}")
    fun deleteJoke(@PathVariable id: Long)
    {
        repository.deleteById(id)
    }
}