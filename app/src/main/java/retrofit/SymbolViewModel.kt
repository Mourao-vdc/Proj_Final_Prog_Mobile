import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit.News
import retrofit.SymbolDetails
import retrofit.SymbolRepository
import retrofit.SymbolSummary
import retrofit.Symbols

class SymbolViewModel : ViewModel() {
    private val repository = SymbolRepository()

    private val _Symbol = MutableLiveData<List<Symbols>>()
    val Symbol: LiveData<List<Symbols>> = _Symbol

    private val _SymbolSummary = MutableLiveData<List<SymbolSummary>>()
    val SymbolSummary: LiveData<List<SymbolSummary>> = _SymbolSummary

    private val _SymbolDetails = MutableLiveData<List<SymbolDetails>>()
    val SymbolDetails: LiveData<List<SymbolDetails>> = _SymbolDetails

    private val _newsData = MutableLiveData<List<News>>()
    val newsData: LiveData<List<News>> = _newsData

    fun fetchSymbolSummary() {
        viewModelScope.launch {
            val response = repository.getSymbolSummary()
            _SymbolSummary.postValue(response)
        }
    }

    fun fetchSymbolDetails() {
        viewModelScope.launch {
            val response = repository.getSymbolDetails()
            _SymbolDetails.postValue(response)
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