package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit.News
import retrofit.SymbolViewModel

class NoticiasViewModel : ViewModel() {
    var listNews: List<News>?
    val data = SymbolViewModel()

    private val _newsList = MutableLiveData<List<News>>()

    val newsList : LiveData<List<News>> get() = _newsList
    init {
        listNews = data.fetchNews()
        _newsList.postValue(listNews)
    }
}