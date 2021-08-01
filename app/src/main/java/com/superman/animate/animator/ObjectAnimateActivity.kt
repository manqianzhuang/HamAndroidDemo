package com.superman.animate.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.superman.animate.R
import kotlinx.android.synthetic.main.activity_object_animate.*

class ObjectAnimateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animate)
        val anim1 = ObjectAnimator.ofFloat(tv_hello_world, "translationX", 0f, -70f, 0f)
        anim1.duration = 1000
        val anim2 = ObjectAnimator.ofFloat(tv_hello_world, "alpha", 1f, 0f, 1f)
        anim2.duration = 1000
        val anim3 = ObjectAnimator.ofFloat(tv_hello_world, "scaleX", 1f, 2f, 1f)
        anim3.duration = 1000
        val anim4 = ObjectAnimator.ofFloat(tv_hello_world, "rotation", 0f, 180f, 0f)
        anim4.duration = 1000
        tv_hello_world.setOnClickListener {
            AnimatorSet().apply {
                playSequentially(anim1, anim2, anim3, anim4)
                start()
            }
        }
    }
}