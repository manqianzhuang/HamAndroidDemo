package com.superman.animate.example.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import dagger.android.support.DaggerAppCompatActivity

/**
 * @ProjectName: Kotlin-Test-master
 * @Package: com.fotoit.kot.jetpack.ui.base
 * @ClassName: BaseActivity
 * @Description: 类的描述
 * @Author: Superman
 * @CreateDate: 2019/11/21 11:28
 * @UpdateUser: Superman
 * @UpdateDate: 2019/11/21 11:28
 * @UpdateRemark: 更新说明
 * @Version: ${BuildConfig.VERSION_NAME}
 */
abstract class BaseActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setFullScreen()
        setContentView(getLayoutId())
        initViews()
        initData()
    }

    abstract fun initData()
    abstract fun initViews()
    abstract fun getLayoutId(): Int

    /**
     * 隐藏状态栏和导航栏
     */
    fun setFullScreen() {
        BarUtils.setNavBarColor(this, Color.TRANSPARENT)
        val uiFlags = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN        //hide statusBar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION   //hide navigationBar
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        window.decorView.systemUiVisibility = uiFlags
    }


}