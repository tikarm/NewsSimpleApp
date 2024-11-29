package tigran.applications.newssimpleapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "sectionName")
    val sectionName: String? = null,
    @ColumnInfo(name = "title")
    val webTitle: String? = null,
)