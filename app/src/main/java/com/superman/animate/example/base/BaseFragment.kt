package com.superman.animate.example.baseimport android.os.Bundleimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport androidx.databinding.ViewDataBindingimport com.blankj.utilcode.util.ToastUtilsimport dagger.android.support.DaggerFragmentabstract class BaseFragment: DaggerFragment(), IView {    abstract fun initEvent()    abstract fun setContentView(): View    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {        return setContentView()    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        initEvent()    }    override fun showLoading() {    }    override fun hideLoading() {    }    override fun showToast(text: String) {        ToastUtils.showShort(text)    }}