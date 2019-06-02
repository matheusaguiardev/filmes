package br.com.aguiar.aguiarcubos.ui.view.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.aguiar.aguiarcubos.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val IMAGEM_KEY_BUNDLE = "IMAGEM_KEY"
        const val DESCRIPTION_KEY_BUNDLE = "DESCRIPTION_KEY"
    }

    val presenter: DetailPresenter by inject()

    lateinit var description: String
    lateinit var pathImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.imgLoaded().observe(this, Observer(::imageObserver))

        description = intent.getStringExtra(DESCRIPTION_KEY_BUNDLE)
        pathImage = intent.getStringExtra(IMAGEM_KEY_BUNDLE)

        setUpLayout(pathImage, description)
    }

    private fun imageObserver(img: Bitmap?) {
        img?.let {
            imgMovie.setImageBitmap(it)
        }
    }

    private fun setUpLayout(path: String, description: String) {
        textDescription.text = if (!description.isEmpty()) description else "(Sem descrição)"
        textDescription.movementMethod = ScrollingMovementMethod()
        presenter.loadImage(path)
    }

}
