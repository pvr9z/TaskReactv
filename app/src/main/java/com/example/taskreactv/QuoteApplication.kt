package com.example.taskreactv

import android.app.Application
import com.example.taskreactv.api.QuoteService
import com.example.taskreactv.api.RetrofitHelper
import com.example.taskreactv.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        quoteRepository = QuoteRepository(quoteService, applicationContext)
    }
}