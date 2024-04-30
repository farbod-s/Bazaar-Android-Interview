package ir.cafebazaar.data.model

import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.network.model.MovieRemoteModel

fun MovieRemoteModel.asLocalModel() = MovieLocalModel(
    id = id,
    title = title ?: "",
    cover = backdropPath ?: posterPath ?: "",
)

fun MovieLocalModel.asExternalModel() = Movie(
    id = id,
    title = title,
    cover = cover,
)