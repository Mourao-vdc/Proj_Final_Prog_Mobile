package com.example.finalproject.Stocks

import SymbolViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit.Symbols

class AtivosViewModel: ViewModel()
{
    private val symbolViewModel = SymbolViewModel()

    val symbolsList: LiveData<List<Symbols>> = symbolViewModel.Symbol

    init {
    }
}