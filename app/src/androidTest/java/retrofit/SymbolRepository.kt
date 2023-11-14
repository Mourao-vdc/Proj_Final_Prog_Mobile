package retrofit

import retrofit2.Response

class SymbolRepository(private val retrofitInterface: retrofitInterface)
{
    suspend fun getSymbols(): Response<List<String>> {
        return retrofitInterface.getSymbols()
    }

    suspend fun getSymbolSummary(): Response<List<SymbolSummary>> {
        return retrofitInterface.getSymbolSummary()
    }

    suspend fun getSymbolDetails(): Response<List<SymbolDetails>> {
        return retrofitInterface.getSymbolDetails()
    }

    suspend fun getNews(): Response<List<News>> {
        return retrofitInterface.getNews()
    }
}