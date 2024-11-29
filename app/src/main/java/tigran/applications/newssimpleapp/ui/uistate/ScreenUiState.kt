package tigran.applications.newssimpleapp.ui.uistate

data class ScreenUiState(
    val newsUiStateList: List<NewsUiState> = emptyList(),
    val screenProgressState: ScreenProgressState = ScreenProgressState.Loaded
)

sealed interface ScreenProgressState {
    class Loading: ScreenProgressState
    data class Error(val message: String): ScreenProgressState
    data object Loaded: ScreenProgressState
}