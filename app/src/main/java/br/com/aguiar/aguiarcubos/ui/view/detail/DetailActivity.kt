package br.com.aguiar.aguiarcubos.ui.view.detail

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aguiar.aguiarcubos.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val IMAGEM_KEY_BUNDLE = "IMAGEM_KEY"
        const val DESCRIPTION_KEY_BUNDLE = "DESCRIPTION_KEY"
    }

    var image: Bitmap? = null
    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*  image = BitmapFactory.decodeByteArray(
              intent.getByteArrayExtra(IMAGEM_KEY_BUNDLE),
              0,
              intent.getByteArrayExtra(IMAGEM_KEY_BUNDLE).size
          )
  */
        // intent.getParcelableExtra()

        description = intent.getStringExtra(DESCRIPTION_KEY_BUNDLE)
        setUpLayout(image, description)
    }

    private fun setUpLayout(img: Bitmap?, description: String) {
        img?.let {
            imgMovie.setImageBitmap(img)
        }
        textDescription.text = description
    }

}
