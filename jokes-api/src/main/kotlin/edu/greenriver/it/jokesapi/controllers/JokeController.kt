package edu.greenriver.it.jokesapi.controllers

import edu.greenriver.it.jokesapi.model.Joke
import edu.greenriver.it.jokesapi.repositories.IJokeRepository
import org.json.JSONException
import org.json.JSONObject
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL


@Controller
class JokeController(private val repository: IJokeRepository)
{
    @RequestMapping("/", "")
    fun originalJoke(model: Model): String
    {
        //Any can be ("Programming","Miscellaneous","Dark", or "Any")
        val jokeFromUrl = URL("https://official-joke-api.appspot.com/random_joke").readText()

        val json = JSONObject(jokeFromUrl)
        val joke = jsonToJokeObject(json)
        model.addAttribute("joke", joke)

        return "index"
    }

    @RequestMapping("/saved")
    fun savedJokes(model: Model): String
    {
        model.addAttribute("jokes", repository.findAll())
        return "saved_jokes"
    }



    fun jsonToJokeObject(json: JSONObject) : Joke
    {
        val id = json.getLong("id")
        val type = json.getString("type")
        val setup: String = json.getString("setup")
        val punchline: String =  json.getString("punchline")

        return Joke(id, type, setup, punchline)
    }


}