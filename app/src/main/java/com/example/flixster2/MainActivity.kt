package com.example.flixster2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException
import android.util.Log
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import com.codepath.asynchttpclient.RequestParams
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Request

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = "YOUR API KEY HERE"

class MainActivity : AppCompatActivity() {
    private lateinit var personsRecyclerView: RecyclerView
    private val persons = mutableListOf<PopularPerson>()
    private lateinit var progressBar: ContentLoadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress)

        personsRecyclerView = findViewById(R.id.personView)

        val personAdapter = PersonAdapter(this, persons)
        personsRecyclerView.adapter = personAdapter

        personsRecyclerView.layoutManager = GridLayoutManager(this, 2)


        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = SEARCH_API_KEY
        client[
                "https://api.themoviedb.org/3/person/popular",
                params,
                object: JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String,
                        throwable: Throwable?
                    ) {
                        progressBar.hide()
                        //progressBar.hide()
                        throwable?.message?.let {
                            Log.e("Person Popular", response)
                        }
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                        Log.i(TAG, "Successfully fetched people: $json")
                        try {
                            val parsedJson = createJson().decodeFromString(
                                BaseResponse.serializer(),
                                json.jsonObject.toString()
                            )

                            parsedJson.results?.let { list ->
                                persons.addAll(list)
                                personAdapter.notifyItemRangeInserted(0,persons.size)
                            }
                            progressBar.hide()


                        } catch (e : JSONException) {
                            Log.e("Main", "Exception: $e")
                        }

                        //recyclerView.adapter = PopularPersonAdapter(movies, this@PopularPersonsFragment)

                        //progressBar.hide()
                        //Log.d("OnGoingMovieFragment", resultsJSON)
                    }

                }
        ]
    }
}