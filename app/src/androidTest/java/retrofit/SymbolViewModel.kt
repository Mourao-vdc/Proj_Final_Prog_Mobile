package retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
class SymbolViewModel(private val repository: SymbolRepository) : ViewModel()
{

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

    fun fetchNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            // Handle the response here
        }
    }
}