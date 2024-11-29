package tigran.applications.newssimpleapp.ui.uistate

data class NewsUiState(
    val id: String,
    val sectionName: String? = null,
    val webPublicationDate: String? = null,
    val webTitle: String? = null,
)
