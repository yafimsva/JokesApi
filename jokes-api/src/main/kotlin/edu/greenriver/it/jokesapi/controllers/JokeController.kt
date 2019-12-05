package edu.greenriver.it.jokesapi.controllers

import edu.greenriver.it.jokesapi.model.Joke
import edu.greenriver.it.jokesapi.repositories.IJokeRepository
import org.json.JSONObject
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL

@Controller
class JokeController(private val repository: IJokeRepository)
{
    @RequestMapping("/")
    fun originalJoke(model: Model): String
    {
        //Any can be ("Programming","Miscellaneous","Dark", or "Any")
        val jokeFromUrl = URL("https://sv443.net/jokeapi/category/Any").readText()
        val json = JSONObject(jokeFromUrl)
        val joke = jsonToJokeObject(json)
        model.addAttribute("joke", joke)
        model.addAttribute("saveJoke", saveJoke(joke))

        return "index"
    }

    @RequestMapping("/saved")
    fun savedJokes(model: Model): String
    {
        model.addAttribute("jokes", repository.findAll())
        return "saved_jokes"
    }

    fun saveJoke(joke:Joke)
    {
        repository.save(joke)
    }

    fun jsonToJokeObject(json: JSONObject) : Joke
    {
        val id = json.getLong("id")
        val category = json.getString("category")
        val type = json.getString("type")
        var setup: String? = null
        var delivery: String? =  null
        var joke: String? = null

        if (type == "single")
        {
            joke = json.getString("joke")
        }
        else
        {
            setup = json.getString("setup")
            delivery =  json.getString("delivery")
        }

        return Joke(id, category, type, setup, delivery, joke)
    }


}