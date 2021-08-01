package com.superman.animate.example

import android.content.Context
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayout
import com.jaeger.library.StatusBarUtil
import com.superman.animate.R
import com.superman.animate.example.base.BaseActivity
import com.superman.animate.example.bean.FragmentItem
import com.superman.animate.example.http.Api
import com.superman.animate.example.util.blur
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.android.synthetic.main.layout_navigation_header.*
import okhttp3.*
import java.io.IOException

class MvvmExampleActivity : BaseActivity() {

    private lateinit var mAdapter: MainViewPageAdapter

//    @Inject
//    lateinit var mHomeFragment: HomeFragment
//    @Inject
//    lateinit var mProjectFragment: BaseFragment
//    @Inject
//    lateinit var mSystemFragment: BaseFragment
//    @Inject
//    lateinit var mGankFragment: BaseFragment

//    private lateinit var remote: IBookRemote

//    private val serviceConnection = object : ServiceConnection {
//        override fun onServiceDisconnected(name: ComponentName?) {
//            LogUtils.e("${name?.className} was disconnected")
//        }
//
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            LogUtils.i("${name?.className} and ${service?.interfaceDescriptor} was connected successfully")
//            remote = IBookRemote.Stub.asInterface(service)
//        }
//    }


    override fun initData() {

        val body = RequestBody.create(MediaType.parse("application/json"), "")
        val request = Request.Builder()
            .addHeader("ss", "ss")
            .method("POST", body)
            .build()
        Api.okHttp.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }

        })
//        bindRemote()
    }

    private fun setupFragment() {
        val list = mutableListOf<FragmentItem>()
//        list.add(FragmentItem("首页", mHomeFragment))
//        list.add(FragmentItem("项目", mProjectFragment))
//        list.add(FragmentItem("体系", mSystemFragment))
//        list.add(FragmentItem("干货", mGankFragment))
        mAdapter = MainViewPageAdapter(this, supportFragmentManager, list)
        vp_main_pager.adapter = mAdapter
        tl_main_tab.setupWithViewPager(vp_main_pager)

        for (i in 0..tl_main_tab.tabCount) {
            val tabView: TabLayout.Tab? = tl_main_tab.getTabAt(i)
            tabView?.customView = mAdapter.getTabView(i)
        }

        // 默认选中第 0 个
        vp_main_pager.currentItem = 0
        changeTabView(tl_main_tab.getTabAt(0), 22f, true)

        tl_main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                changeTabView(tab, 18f, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                changeTabView(tab, 22f, true)
            }
        })
    }

    override fun initViews() {
        StatusBarUtil.setColorForDrawerLayout(this, dl_drawer_layout, resources.getColor(R.color.colorPrimary), 0)
        iv_main_menu?.setOnClickListener {
            openDrawer()
        }
        //setupFragment()
//        tv_nav_username?.setOnClickListener{
//            when(it.id){
//                R.id.iv_main_search -> {
//                    //gotoSearchActivity()
//                    overridePendingTransition(0, 0)
//                }
//                R.id.tv_nav_username -> {
//
//                }
//            }
//        }

//        nv_left_navigation.setNavigationItemSelectedListener { item ->
//            //closeDrawer()
//            when (item.itemId) {
//                R.id.item_nav_happy_minute -> {
//                    remote.addBook(Book("Java编程思想", "外国佬"))
//                    LogUtils.json(remote.books)
//                }
//                R.id.item_nav_favorite -> {
//                    remote.removeBook(3)
//                    LogUtils.json(remote.books)
//                    //gotoActivity(mContext as Activity, FavoriteActivity().javaClass)
//                }
//                R.id.item_nav_setting -> {
//                    //gotoActivity(mContext as Activity, SettingActivity().javaClass)
//                }
//                R.id.item_nav_about -> {
//                    //gotoActivity(mContext as Activity, AboutActivity().javaClass)
//                }
//            }
//            true
//        }

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.avatar)
        iv_avatar_background.setImageBitmap(blur(this, bitmap, 22))

    }

    //AIDL 远程服务示例
//    private fun bindRemote() {
//        val service = Intent(this, AIDLService::class.java).apply {
//            action = "com.superman.wanandroid.ACTION_BOOK_MANAGER"
//            setPackage("com.superman.wanandroid")
//        }
//        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE)
//    }

    private fun closeDrawer() {
        dl_drawer_layout.closeDrawer(GravityCompat.END)
    }

    private fun openDrawer() {
        dl_drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun changeTabView(tab: TabLayout.Tab?, textSize: Float, isSelected: Boolean) {
        val view: View? = tab?.customView
        val textView: TextView? = view?.findViewById(R.id.tv_tab_title)
        textView?.textSize = textSize
        if (isSelected) {
            textView?.setTextColor(resources.getColor(android.R.color.black))
            val width = textView?.measuredWidth
            Log.e("debug", "width = $width")
        } else {
            textView?.setTextColor(resources.getColor(R.color.gray_959698))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.e("onConfigurationChanged: orientation = ${newConfig.orientation}")
    }

    override fun onDestroy() {
        super.onDestroy()
//        unbindService(serviceConnection)
    }

    class MainViewPageAdapter(
        var context: Context, fm: FragmentManager,
        private var fragmentItems: List<FragmentItem>
    ) : FragmentPagerAdapter(fm) {

        private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

        override fun getItem(position: Int): Fragment {
            return fragmentItems[position].fragment
        }

        override fun getCount(): Int {
            return fragmentItems.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentItems[position].title
        }

        fun setDataSource(list: List<FragmentItem>) {
            fragmentItems = list
            notifyDataSetChanged()
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

        fun getTabView(position: Int): View {
            val view: View = layoutInflater.inflate(R.layout.layout_main_tab, null, false)
            val textView: TextView = view.findViewById(R.id.tv_tab_title)
            textView.text = getPageTitle(position)
            textView.setTextColor(context.resources.getColor(R.color.color_666))
            textView.textSize = 18f
            return view
        }
    }


}