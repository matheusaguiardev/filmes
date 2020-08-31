package br.com.aguiar.aguiarmovies.ui.view.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.aguiar.aguiarmovies.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), DetailContract.ContractView {

    companion object {
        const val IMAGE_KEY_BUNDLE = "IMAGE_KEY"
        const val DESCRIPTION_KEY_BUNDLE = "DESCRIPTION_KEY"
    }

    override val presenter: DetailContract.ContractPresenter by inject()

    private lateinit var description: String
    private lateinit var pathImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.imgLoaded().observe(this, Observer(::imageObserver))

        description = intent.getStringExtra(DESCRIPTION_KEY_BUNDLE)
        pathImage = intent.getStringExtra(IMAGE_KEY_BUNDLE)

        setUpLayout(pathImage, description)
    }

    private fun imageObserver(img: Bitmap?) {
        img?.let {
            imgMovie.setImageBitmap(it)
        }
    }

    private fun setUpLayout(path: String, description: String) {
        textDescription.text = if (description.isNotEmpty()) description else "(Sem descrição)"
        textDescription.movementMethod = ScrollingMovementMethod()
        presenter.loadImage(path)
    }

}
