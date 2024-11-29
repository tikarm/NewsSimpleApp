package tigran.applications.newssimpleapp.domain.model

data class NewsModel(
    val id: String,
    val sectionID: String? = null,
    val sectionName: String? = null,
    val webPublicationDate: String? = null,
    val webTitle: String? = null,
)