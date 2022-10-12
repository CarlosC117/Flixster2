package com.example.flixster2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

class PersonAdapter (private val context: Context, private val persons: List<PopularPerson>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.person_basic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = persons.size



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // var goes here. Ex:
        //var movieImage: ImageView = mView.findViewById(id.movie_image)
        var mItem: PopularPerson? = null
        var authorProfile: ImageView = itemView.findViewById(R.id.profile_picture)
        var authorName: TextView = itemView.findViewById(R.id.name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val person = persons[absoluteAdapterPosition]

            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("Extra", person)
            context.startActivity(intent)
        }


        // override fun onClick(v: View?) {
        // val person = persons[1]
        //val intent = Intent(context, MainActivity2::class.java)
        //intent.putExtra("extraData", "hi")
        //context.startActivity(intent)
        // }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        val link = "https://image.tmdb.org/t/p/w500/" + person.authorPoster

        holder.mItem = person
        holder.authorName.text = person.nameAuthor

        Glide.with(holder.itemView).load(link).centerInside().into(holder.authorProfile)
    }





    }