package tigran.applications.newssimpleapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tigran.applications.newssimpleapp.domain.usecase.GetNewsUseCase

class NewsViewModelFactory(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(getNewsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}