package edu.greenriver.it.jokesapi.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Joke (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var type: String,
        var setup: String,
        var punchline: String)
