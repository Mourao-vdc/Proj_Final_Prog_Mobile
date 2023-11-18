package retrofit

import retrofit2.Response
import retrofit2.http.GET

interface retrofitInterface
{
    @GET("/api/symbols")
    suspend fun getSymbols(): Response<List<String>>

    @GET("/api/symbols")
    suspend fun getSymbolSummary(): Response<List<SymbolSummary>>

    @GET("/api/symbols")
    suspend fun getSymbolDetails(): Response<List<SymbolDetails>>

    @GET("/api/news")
    suspend fun getNews(): Response<List<News>>
}