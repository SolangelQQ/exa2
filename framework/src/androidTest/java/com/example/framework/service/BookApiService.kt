package com.example.framework.service


import com.example.framework.dto.BookResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<BookSearchResponse>
}

data class BookSearchResponse(
    @SerializedName("docs") val books: List<BookResponse>
)