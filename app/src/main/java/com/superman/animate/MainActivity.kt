package com.superman.animate

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jaeger.library.StatusBarUtil
import com.superman.animate.aidl.AIDLActivity
import com.superman.animate.animator.ObjectAnimateActivity
import com.superman.animate.customview.day1.CustomViewBitmapShaperActivity
import com.superman.animate.example.MvvmExampleActivity
import com.superman.animate.design_mode.DesignModeActivity
import com.superman.animate.event.AndroidEventActivity
import com.superman.animate.handlerthread.HandlerTestActivity
import com.superman.animate.navigation.NavigationTestActivity
import com.superman.animate.rxjava.RxjavaTestActivity
import com.superman.animate.thread.ThreadTestActivity
import com.superman.animate.widget.LargeImageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_navigation_test.*

class MainActivity : AppCompatActivity() {

    private var adapter: BaseQuickAdapter<String, BaseViewHolder> ?= null

    companion object {
        val btnStrings = arrayListOf(
            "动画",
            "设计模式",
            "HandlerThread",
            "高清大图",
            "线程",
            "android事件机制",
            "rxjava",
            "协程",
            "AIDL",
            "Navigation",
            "CustomViewBitmapShaper"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StatusBarUtil.setLightMode(this)
        setSupportActionBar(toolbar)

        LogUtils.i("像素密度:"+ScreenUtils.getScreenDensity())
        LogUtils.i("像素dip值:"+ScreenUtils.getScreenDensityDpi())
        LogUtils.i("px:"+ScreenUtils.getScreenDensityDpi()*ScreenUtils.getScreenDensity())
        adapter = TestClickAdapter(R.layout.component_button, btnStrings).apply {
            setOnItemClickListener { _, _, position ->
                startActivity(
                    Intent(this@MainActivity, when(btnStrings[position]) {
                        "HandlerThread"-> HandlerTestActivity::class.java
                        "设计模式"-> DesignModeActivity::class.java
                        "动画"-> ObjectAnimateActivity::class.java
                        "高清大图"-> LargeImageActivity::class.java
                        "线程"-> ThreadTestActivity::class.java
                        "android事件机制"-> AndroidEventActivity::class.java
                        "rxjava"-> RxjavaTestActivity::class.java
                        "协程"-> MvvmExampleActivity::class.java
                        "AIDL"-> AIDLActivity::class.java
                        "Navigation"-> NavigationTestActivity::class.java
                        "CustomViewBitmapShaper"-> CustomViewBitmapShaperActivity::class.java
                        else -> ObjectAnimateActivity::class.java
                    })
                )
                overridePendingTransition(R.anim.page_in, R.anim.page_out)
            }
        }
        rv_btns.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        rv_btns.adapter = adapter
    }
}