package ir.cafebazaar.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieLocalModel(
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0,
    val id: Long,
    val title: String,
    val cover: String,
)