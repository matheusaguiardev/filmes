package br.com.aguiar.aguiarmovies.ui.view.home.fragments.drama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.aguiar.aguiarmovies.R
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarmovies.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_drama.*
import org.koin.android.ext.android.inject

class DramaFragment : Fragment(), DramaContract.DramaView {

    override val presenter: DramaContract.DramaPresenter by inject()

    val imgProvider: PicassoRepository by inject()
    private val adapter by lazy { MovieListAdapter(imgProvider, callbackClick) }

    lateinit var callbackClick: ((MovieDetail) -> Unit)

    companion object {
        fun newInstance(callback: ((MovieDetail) -> Unit)): DramaFragment {
            val frag = DramaFragment()
            frag.callbackClick = callback
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.dramaMovie().observe(viewLifecycleOwner, Observer(::observerMovie))
    }

    override fun onStart() {
        super.onStart()
        presenter.fetchMovies()
    }

    private fun observerMovie(result: MovieList?) {
        result?.let {
            adapter.setList(result.result)
            list.layoutManager = GridLayoutManager(requireActivity(), 2)
            list.adapter = adapter
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setProgressVisibility(visibility: Int) {
        list.visibility = when(visibility) {
            View.INVISIBLE, View.GONE ->  View.VISIBLE
            else -> View.GONE
        }
        progressBar.visibility = visibility
    }

    override fun emptyState(visibility: Int) {
        msgEmptyStateTextView.visibility = visibility
        list.visibility = when(visibility) {
            View.INVISIBLE, View.GONE ->  View.VISIBLE
            else -> View.GONE
        }
    }

}