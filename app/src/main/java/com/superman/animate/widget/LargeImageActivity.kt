package com.superman.animate.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.superman.animate.R
import kotlinx.android.synthetic.main.activity_large_image.*
import java.io.IOException

class LargeImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_image)
        try {
            val iStream = assets.open("qm.jpg")
            large_image.setInputStream(iStream)
        } catch (e: IOException) {
            print(e.message)
        }
    }
}