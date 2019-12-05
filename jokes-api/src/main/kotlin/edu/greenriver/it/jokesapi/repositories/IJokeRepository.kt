package edu.greenriver.it.jokesapi.repositories

import edu.greenriver.it.jokesapi.model.Joke
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface IJokeRepository : CrudRepository<Joke, Long>
