package tigran.applications.newssimpleapp.data.remote.data

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("id") val id: String,
    @SerializedName("sectionName") val sectionName: String? = null,
    @SerializedName("webTitle") val webTitle: String? = null,
)
