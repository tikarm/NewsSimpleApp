package tigran.applications.newssimpleapp.domain.model

data class NewsModel(
    val id: String,
    val sectionName: String? = null,
    val webTitle: String? = null,
)