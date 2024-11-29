package tigran.applications.newssimpleapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tigran.applications.newssimpleapp.domain.usecase.GetNewsUseCase

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {
    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke()
        }
    }
}