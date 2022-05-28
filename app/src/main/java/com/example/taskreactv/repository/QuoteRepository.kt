package com.example.taskreactv.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskreactv.api.QuoteService
import com.example.taskreactv.models.QuoteList
import com.example.taskreactv.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class QuoteRepository(
    private val quoteService: QuoteService,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    fun getQuotes() {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            Observable
                .defer { Observable.just((Math.random() * 10).toInt()) }
                .flatMap{number -> quoteService.getQuotes(number)}
                .repeatWhen { completed -> completed.delay(10, TimeUnit.SECONDS) }
                .subscribeOn(Schedulers.io())
                .subscribe { quotesLiveData.postValue(it) }
        }
    }
}







