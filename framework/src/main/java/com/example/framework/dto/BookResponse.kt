package com.example.framework.dto

import com.example.book.domain.entity.Book
import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("key") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authors: List<String>?,
    @SerializedName("cover_i") val coverId: Int?,
    @SerializedName("first_publish_year") val publishYear: Int?,
    @SerializedName("description") val description: String?
) {
    fun toDomain(): Book {
        return Book(
            id = id,
            title = title,
            author = authors?.joinToString(", "),
            coverUrl = coverId?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" },
            publishYear = publishYear,
            description = description?.let {
                if (it is String) it else (it as? Map<String, String>)?.get("value")
            }
        )
    }
}