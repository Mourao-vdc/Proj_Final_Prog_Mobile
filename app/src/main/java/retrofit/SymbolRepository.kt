package retrofit

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

class SymbolRepository()
{
    private val retrofitInterface = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
    private var symbolList : List<String> = emptyList()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var tickersListDeferred: Deferred<List<String>>? = null

    init {
        getSymbols()
    }

    fun getSymbols() {
        Log.i("Warning", "Not values 1111")
        tickersListDeferred = coroutineScope.async {
            val symbolsResponse = retrofitInterface.getSymbols()
            if (symbolsResponse.isSuccessful) {
                Log.i("Warning", "Not values 2222")//nao entra
                symbolList = symbolsResponse.body() ?: emptyList()
                symbolList // Return the fetched list
            } else {
                Log.i("Warning", "Not values 3333")//nao entra
                emptyList()
            }
        }
    }

    suspend fun getSymbolsList(): List<String> {
        return tickersListDeferred?.await() ?: emptyList()
    }

    suspend fun getSymbolSummary(): MutableList<SymbolSummary> {
        val SymbolsList = getSymbolsList()
        val symbolSummaryList = mutableListOf<SymbolSummary>()
        for (symbol in symbolList){
            val response = retrofitInterface.getSymbolSummary(symbol)
            if (response.isSuccessful){
                val tickerSummary = response.body()
                if (tickerSummary != null) {
                    symbolSummaryList.add(tickerSummary)
                }

            }
        }
        return symbolSummaryList
    }

    suspend fun getSymbolDetails(): MutableList<SymbolDetails> {
        val SymbolsList = getSymbolsList()
        val symbolDetailsList = mutableListOf<SymbolDetails>()
        for (symbol in symbolList){
            val response = retrofitInterface.getSymbolDetails(symbol)
            if (response.isSuccessful){
                val tickerSummary = response.body()
                if (tickerSummary != null) {
                    symbolDetailsList.add(tickerSummary)
                }

            }
        }
        return symbolDetailsList
    }

    suspend fun getNews(): Response<List<News>> {
        return retrofitInterface.getNews()
    }
}