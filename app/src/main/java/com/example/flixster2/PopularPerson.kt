package com.example.flixster2

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BaseResponse (
    @SerialName("results")
    val results: List<PopularPerson>?
)

@Keep
@Serializable
data class PopularPerson(
   @SerialName("name")
   val nameAuthor: String?,
   @SerialName("profile_path")
   var authorPoster: String?,
   @SerialName("known_for")
   var known_for: List<KnownFor>?,

) : java.io.Serializable


@Keep
@Serializable
data class KnownFor(
    @SerialName("title")
    val movieTitle: String? = null,
    @SerialName("name")
    val movieName: String? = null,
    @SerialName("overview")
    var movieDescription: String?,
    @SerialName("poster_path")
    var moviePoster: String?,
) : java.io.Serializable
//class PopularPerson {
//    @SerializedName("name")
//    var nameAuthor: String? = null
//
//    @SerializedName("profile_path")
//    var authorPoster: String? = null
//
//    @SerializedName("poster_path")
//    var moviePoster: String? = null
//
//    @SerializedName("title")
//    var movieTitle: String? = null
//
//    @SerializedName("overview")
//    var movieDescription: String? = null
//}