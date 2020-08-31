package br.com.aguiar.aguiarmovies.repository.movies

import br.com.aguiar.aguiarmovies.domain.repository.movies.MoviesRepository
import br.com.aguiar.aguiarmovies.mocks.getMovieMock
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesTest {

    private val repository = mock<MoviesRepository>()

    private val generos = listOf(1, 2)
    private val nomeFilme = "vingadores"

    @Test
    fun `baixar lista de filmes`() = runBlocking {
        `dado que eu tenha uma lista de filmes para baixar`()
        `quando eu executar o metodo de chamada dos filmes que desejo baixar`()
        `entao verificar se a logica foi executada e retornada`()
    }

    @Test
    fun `buscar filme com query`() = runBlocking {
        `dado que eu queira buscar filmes pelo nome`()
        `quando executar o metodo para buscar o filme desejado`()
        `entao verificar se a logica de buscar de filme foi executada`()
    }

    /*************** DADO ******************/
    private suspend fun `dado que eu tenha uma lista de filmes para baixar`() {
        whenever(repository.fetchMovies(generos))
            .thenReturn(getMovieMock())
    }

    private suspend fun `dado que eu queira buscar filmes pelo nome`() {
        whenever(repository.searchMovie(nomeFilme))
            .thenReturn(getMovieMock())
    }

    /*************** QUANDO ******************/
    private suspend fun `quando eu executar o metodo de chamada dos filmes que desejo baixar`() {
        repository.fetchMovies(generos)
    }

    private suspend fun `quando executar o metodo para buscar o filme desejado`() {
        repository.searchMovie(nomeFilme)
    }


    /*************** ENTÃO ******************/
    private suspend fun `entao verificar se a logica foi executada e retornada`() {
        verify(repository).fetchMovies(generos)
    }

    private suspend fun `entao verificar se a logica de buscar de filme foi executada`() {
        verify(repository).searchMovie(nomeFilme)
    }

}