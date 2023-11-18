package retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class SymbolViewModel() : ViewModel()
{
    val repository = SymbolRepository()

    // Functions to fetch data
    fun fetchSymbols() {
        viewModelScope.launch {
            val response = repository.getSymbols()
            // Handle the response here
        }
    }

    fun fetchSymbolSummary() {
        viewModelScope.launch {
            val response = repository.getSymbolSummary()
            // Handle the response here
        }
    }

    fun fetchSymbolDetails() {
        viewModelScope.launch {
            val response = repository.getSymbolDetails()
            // Handle the response here
        }
    }

    fun fetchNews():List<News>? {
        lateinit var response:Response<List<News>>
        viewModelScope.launch {
            response = repository.getNews()
        }
        return response.body()
    }
}