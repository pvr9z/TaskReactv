package com.example.taskreactv.api

import com.example.taskreactv.models.QuoteList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    fun getQuotes(@Query("page") page: Int): Observable<QuoteList>

}