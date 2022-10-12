package com.example.flixster2

import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


private const val TAG = "MainActivity2"

class MainActivity2 : AppCompatActivity() {
    private lateinit var imageOfAuthor: ImageView
    private lateinit var imageOfMovie: ImageView
    private lateinit var title: TextView
    private lateinit var known: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // TODO: Find the views for the screen
        imageOfAuthor = findViewById(R.id.authorImage)
        imageOfMovie = findViewById(R.id.movieImage)
        title = findViewById(R.id.authorTitle)
        known = findViewById(R.id.knownFor)
        overview = findViewById(R.id.description)

        val person = intent.getSerializableExtra("Extra") as PopularPerson

        val movieUrl = "https://image.tmdb.org/t/p/w500/" + person.known_for?.get(0)?.moviePoster
        val personUrl = "https://image.tmdb.org/t/p/w500/" + person.authorPoster

        title.text = person.nameAuthor
        if (person.known_for?.get(0)?.movieTitle != null) {
            known.text = "Known for: " + person.known_for?.get(0)?.movieTitle
        } else if (person.known_for?.get(0)?.movieName != null){
            known.text = "Known for: " + person.known_for?.get(0)?.movieName
        } else {
            known.text = "Known for: No movie title found!"
        }

        overview.text = person.known_for?.get(0)?.movieDescription

        Glide.with(this).load(movieUrl). into(imageOfMovie)
        Glide.with(this).load(personUrl).into(imageOfAuthor)

    }
}