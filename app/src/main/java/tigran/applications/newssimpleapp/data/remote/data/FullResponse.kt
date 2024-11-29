package tigran.applications.newssimpleapp.data.remote.data

import com.google.gson.annotations.SerializedName

data class FullResponse(
    @SerializedName("response") val response: Response? = null,
)