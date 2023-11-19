import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit.News
import retrofit.SymbolRepository
import retrofit2.Response

class SymbolViewModel : ViewModel() {
    private val repository = SymbolRepository()

    private val _newsData = MutableLiveData<List<News>>()
    val newsData: LiveData<List<News>> = _newsData

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
            if (response.isSuccessful) {
                _newsData.postValue(response.body())
            } else {
                // Handle error case
            }
        }
    }
}