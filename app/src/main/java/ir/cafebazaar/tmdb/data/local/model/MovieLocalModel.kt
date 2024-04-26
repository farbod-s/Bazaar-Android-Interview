package ir.cafebazaar.tmdb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.cafebazaar.tmdb.data.model.Movie

@Entity(tableName = "movies")
data class MovieLocalModel(
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0,

    val id: Long = -1,
    val title: String,
    val cover: String,
)

fun MovieLocalModel.asExternalModel() = Movie(
    id = id,
    title = title,
    cover = cover,
)