package com.superman.maidlimport android.app.Serviceimport android.content.Intentimport android.os.IBinderimport com.blankj.utilcode.util.LogUtilsclass BookService: Service() {    private val books = mutableListOf<BookBean>()    override fun onCreate() {        super.onCreate()        initData()    }    private fun initData() {        books.add(BookBean("第一行代码", "郭林", "2020-10-10"))        books.add(BookBean("android开发艺术探索", "任玉刚", "2020-10-12"))    }    override fun onBind(intent: Intent?): IBinder = object : IBookRemote.Stub() {        override fun addBook(book: BookBean?) {            LogUtils.w("新增了一本：" + book?.name)            books.add(book!!)        }        override fun removeBook(index: Int) {            if (index>=books.size)                return            LogUtils.w("删除了：" + books[index].name)            books.removeAt(index)        }        override fun queryBooks(): List<BookBean> {            LogUtils.w("所有书本：$books")            return books        }    }}