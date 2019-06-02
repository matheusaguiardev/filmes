package br.com.aguiar.aguiarcubos.repository.movies

import br.com.aguiar.aguiarcubos.domain.repository.movies.MoviesRepository
import br.com.aguiar.aguiarcubos.mocks.getMovieMock
import br.com.aguiar.aguiarcubos.repository.mock
import br.com.aguiar.aguiarcubos.repository.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify

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