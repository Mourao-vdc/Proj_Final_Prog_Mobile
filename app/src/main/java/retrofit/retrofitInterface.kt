package retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface retrofitInterface
{
    @GET("/api/symbols")
    suspend fun getSymbols(): Response<List<String>>

    //Path server para incluir valores no segmento do caminho da URl
    @GET("/api/symbol/summary/{symbol}")
    suspend fun getSymbolSummary(@Path("symbol") symbol: String): Response<SymbolSummary>

    //Path server para incluir valores no segmento do caminho da URl
    @GET("/api/symbol/details/{symbol}")
    suspend fun getSymbolDetails(@Path("symbol") symbol: String): Response<SymbolDetails>

    @GET("/api/news")
    suspend fun getNews(): Response<List<News>>
}