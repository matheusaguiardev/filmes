package br.com.aguiar.aguiarcubos.domain.model.movies

data class MovieDetail(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val originalTitle: String,
    val genreIds: List<Int>,
    val overview: String,
    val releaseDate: String,
    val posterPath: String?
)