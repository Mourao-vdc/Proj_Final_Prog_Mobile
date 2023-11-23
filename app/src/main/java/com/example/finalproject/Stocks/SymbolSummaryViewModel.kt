package com.example.finalproject.Stocks

import SymbolViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit.SymbolSummary

class SymbolSummaryViewModel: ViewModel()
{
    private val symbolViewModel = SymbolViewModel()

    val symbolDetails: LiveData<List<SymbolSummary>> = symbolViewModel.SymbolSummary

    init {
        symbolViewModel.fetchSymbolSummary()
    }
}