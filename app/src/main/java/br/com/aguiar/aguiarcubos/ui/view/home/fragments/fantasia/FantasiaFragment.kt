package br.com.aguiar.aguiarcubos.ui.view.home.fragments.fantasia

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.aguiar.aguiarcubos.R
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarcubos.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_fantasia.*
import org.koin.android.ext.android.inject

class FantasiaFragment : Fragment() {

    val presenter: FantasiaPresenter by inject()
    val imgProvider: PicassoRepository by inject()
    val adapter by lazy { MovieListAdapter(imgProvider, callbackClick) }

    lateinit var callbackClick: ((MovieDetail, Bitmap?) -> Unit)

    companion object {
        fun newInstance(callback: ((MovieDetail, Bitmap?) -> Unit)): FantasiaFragment {
            val frag = FantasiaFragment()
            frag.callbackClick = callback
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_fantasia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.fantasyMovie().observe(viewLifecycleOwner, Observer(::observerMovie))
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

}