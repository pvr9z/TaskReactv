package com.example.taskreactv.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskreactv.models.QuoteList
import com.example.taskreactv.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes()
        }
    }

    val quotes: LiveData<QuoteList>
        get() = repository.quotes
}