package com.superman.animate.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.superman.animate.R
import kotlinx.android.synthetic.main.activity_android_event.*

class AndroidEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_android_event)
        iv_android_event.setImageResource(R.mipmap.android_event)
    }
}