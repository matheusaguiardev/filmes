package br.com.aguiar.aguiarcubos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.aguiarcubos.R
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarcubos.ui.viewholder.MovieViewHolder

class MovieListAdapter(
    private val imgProvider: PicassoRepository,
    private val callback: (MovieDetail) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var dataSource: List<MovieDetail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_movie, parent, false)
        return MovieViewHolder(view, imgProvider)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataSource[position], callback)
    }

    fun setList(list: List<MovieDetail>) {
        dataSource = list
        notifyDataSetChanged()
    }
}