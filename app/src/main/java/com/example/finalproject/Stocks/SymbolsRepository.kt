package com.example.finalproject.Stocks

import retrofit.SymbolRepository

object SymbolsRepository {
    private val symbolRepository = SymbolRepository()

    fun getRepository(): SymbolRepository {
        return symbolRepository
    }

    init {
        symbolRepository.symbolList.clear()
    }
}