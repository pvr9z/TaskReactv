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

    fun getQuotes(page: Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val observable: Observable<QuoteList> = quoteService.getQuotes(page)
            observable.subscribeOn(Schedulers.io())
                .repeatWhen { completed -> completed.delay(10, TimeUnit.SECONDS) }
                .subscribe { quotesLiveData.postValue(it) }
        }
    }
}







