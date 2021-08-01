package com.superman.animate.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.superman.animate.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RxjavaTestActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_test)

        /**
         * rxjava基本原理分析
         *   父类：Observable包含一个重要的抽象函数 subscribeActual，它是上游和下游贯通的桥梁
         *   它做了这些事情：1. 创建发射器emitter、
         *                2. 把观察者observer绑定到emitter
         *                3. 把消息源绑定到emitter
         * 1. 创建事件源
         *   a. 创建继承于Observable的ObservableCreate对象，
         *   b. 把事件源ObservableOnSubscribe传递进去, 并赋值为source
         *   c. 重写subscribeActual(observer)方法，创建emitter接口
         *      （包含onNext，onError，onComplete，onDisposed方法）
         *   d. 消息源与发射器进行订阅关系，source.subscribe(emitter)
         * 2. 创建接收事件的observer对象
         *   a. 创建observer对象
         *   b. 调用subscribe(observer)方法把observer对象传递进去
         *   c. 调用subscribeActual方法，完成订阅关系
         * 3. 用户调用emitter.onNext发送数据
         *
         * 番外：
         *  1. 订阅到工作线程
         *    a. Schedulers.io()
         */

        disposable = Observable
            .create(ObservableOnSubscribe<String> {
                for (i in 0..2) {
                    it.onNext(String.format("事件%s", i))
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                LogUtils.i(it)
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}