package com.example.finalproject.Stocks

import SymbolViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit.SymbolDetails

class SymbolDetailsViewModel: ViewModel()
{
    private val symbolViewModel = SymbolViewModel()

    val symbolDetails: LiveData<List<SymbolDetails>> = symbolViewModel.SymbolDetails

    init {

    }
}