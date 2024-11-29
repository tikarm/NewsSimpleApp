package tigran.applications.newssimpleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.usecase.GetNewsUseCase
import tigran.applications.newssimpleapp.ui.uistate.NewsUiState
import tigran.applications.newssimpleapp.ui.uistate.ScreenProgressState
import tigran.applications.newssimpleapp.ui.uistate.ScreenUiState

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _screenUiState = MutableLiveData(ScreenUiState())
    val screenUiState: LiveData<ScreenUiState> = _screenUiState

    fun getNews() {
        viewModelScope.launch {
            try {
                _screenUiState.value = _screenUiState.value?.copy(
                    screenProgressState = ScreenProgressState.Loading()
                )

                val newsList = getNewsUseCase()

                _screenUiState.value = _screenUiState.value?.copy(
                    newsUiStateList = newsList.map { it.toNewsUiState() },
                    screenProgressState = ScreenProgressState.Loaded
                )
            } catch (e: Exception) {
                _screenUiState.value = _screenUiState.value?.copy(
                    screenProgressState = ScreenProgressState.Error(e.message ?: "Unknown error")
                )
            }
        }
    }

    private fun NewsModel.toNewsUiState(): NewsUiState {
        return NewsUiState(
            id = id,
            sectionName = sectionName,
            webTitle = webTitle
        )
    }
}