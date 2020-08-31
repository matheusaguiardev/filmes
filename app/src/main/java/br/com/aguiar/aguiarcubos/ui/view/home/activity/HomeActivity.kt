package br.com.aguiar.aguiarcubos.ui.view.home.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.aguiar.aguiarcubos.R
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.repository.imagens.PicassoRepository
import br.com.aguiar.aguiarcubos.ui.adapter.MovieListAdapter
import br.com.aguiar.aguiarcubos.ui.adapter.PagerAdapter
import br.com.aguiar.aguiarcubos.ui.extension.toGone
import br.com.aguiar.aguiarcubos.ui.extension.toInvisible
import br.com.aguiar.aguiarcubos.ui.extension.toVisible
import br.com.aguiar.aguiarcubos.ui.view.detail.DetailActivity
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.acao.ActionFragment
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.drama.DramaFragment
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.fantasia.FantasyFragment
import br.com.aguiar.aguiarcubos.ui.view.home.fragments.ficcao.FictionFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    val imgProvider: PicassoRepository by inject()
    override val presenter by inject<HomeContract.HomePresenter>()
    private val adapterList by lazy { MovieListAdapter(imgProvider, ::callbackClickItem) }

    private val adapter by lazy {
        PagerAdapter(
            this,
            supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
        initView()
        setUpPage()
    }

    private fun setUpPage() {
        with(adapter) {
            addFragment(ActionFragment.newInstance(::callbackClickItem))
            addFragment(DramaFragment.newInstance(::callbackClickItem))
            addFragment(FantasyFragment.newInstance(::callbackClickItem))
            addFragment(FictionFragment.newInstance(::callbackClickItem))
            pager.adapter = this
        }
        tabs.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_item, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            observerSearchText(searchView)
        }
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)
    }

    private fun observerSearchText(searchText: SearchView) {
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovieMode(query?.isEmpty() ?: true)
                Toast.makeText(this@HomeActivity, "Pesquisar: $query", Toast.LENGTH_SHORT).show()
                progress.toVisible()
                presenter.fetchMovies(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() != false) {
                    searchMovieMode(true)
                }
                return true
            }
        })
    }

    private fun observerMovie(result: MovieList?) {
        result?.let {
            adapterList.setList(result.result)
            listGeneric.layoutManager = GridLayoutManager(this, 2)
            listGeneric.adapter = adapterList
            progress.toGone()
        }
    }

    private fun callbackClickItem(content: MovieDetail) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.IMAGE_KEY_BUNDLE, content.posterPath)
        intent.putExtra(DetailActivity.DESCRIPTION_KEY_BUNDLE, content.overview)
        startActivity(intent)
    }

    private fun searchMovieMode(isEmpty: Boolean) {

        if (isEmpty) {
            pager.toVisible()
            tabs.toVisible()
            listGeneric.toInvisible()
        } else {
            pager.toInvisible()
            tabs.toGone()
            listGeneric.toVisible()
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun initView() {
        with(presenter){
            attachView(this@HomeActivity)
            genericMovie().observe(this@HomeActivity, Observer(::observerMovie))
        }
    }

}
