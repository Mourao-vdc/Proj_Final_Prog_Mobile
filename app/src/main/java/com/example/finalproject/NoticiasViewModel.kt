package com.example.finalproject

import SymbolViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit.News

class NoticiasViewModel : ViewModel() {
    private val symbolViewModel = SymbolViewModel()

    val newsList: LiveData<List<News>> = symbolViewModel.newsData

    init {
        symbolViewModel.fetchNews()
    }
}
