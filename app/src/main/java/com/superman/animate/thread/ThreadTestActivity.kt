package com.superman.animate.thread

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.superman.animate.R
import com.superman.animate.design_mode.chain_resposbility.LoggerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.util.concurrent.*
import kotlin.random.Random

class ThreadTestActivity : AppCompatActivity() {
    //非核心线程在挂起状态下存活的时间
    private val keepAliveTime = 30_000L

    //固定大小的任务队列
    private val arrayQueue = ArrayBlockingQueue<Runnable>(4)

    //无固定大小的任务队列
    private val linkedQueue = LinkedBlockingDeque<Runnable>()

    /**
     * 普通线程池创建
     */
    private var executor: ExecutorService = ThreadPoolExecutor(
        2,  //核心线程数量
        5,  //最大同时执行任务的线程数量
        keepAliveTime,  //非核心线程在挂起状态下存活的时间
        TimeUnit.MILLISECONDS,  //时间单位
        linkedQueue  //任务队列
    )

    /**
     * 单条线程池
     * 特点： 消息队列的任务按顺序执行
     */
    private val singleExecutor = Executors.newSingleThreadExecutor()

    /**
     * 固定数量池
     * 特点：固定的核心线程数量，
     *  1. 当任务数量小于核心线程数量时，仍旧初始化固定的线程数量。
     *  2. 当任务数量大于核心线程数量时，把剩余的任务加入到队列中。当某线程处理完任务后，从任务队列中取出新的任务继续执行
     */
    private val fixExecutor = Executors.newFixedThreadPool(2)

    /**
     * 缓存线程池
     * 特点：如果线程数量大于需要处理的任务，可灵活回收线程
     */
    private val cacheExecutor = Executors.newCachedThreadPool()

    /**
     * 定时线程池
     * 特点： 定时执行任务
     */
    private val scheduleExecutor = Executors.newScheduledThreadPool(2)

    @Volatile //加volatile关键字是为了预防多线程时，取同一个变量的内存出现差异
    var numbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)
        val latch = CountDownLatch(8)

        Thread {
            for (i in 0..8) {
                val runnable = Runnable {
                    try {
                        Thread.sleep(3_000L)
                        print("run : $i 当前线程：" + Thread.currentThread().name)
                        numbers.add(i)
                        latch.countDown()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                cacheExecutor.execute(runnable)
                //executor.execute(runnable)
                //singleExecutor.execute(runnable)
            }
            cacheExecutor.shutdown()
            latch.await()
            numbers.add(10086)
            LogUtils.e("numbers = $numbers")
        }.start()

    }

    @Synchronized
    private fun getRandomNumber(): Int {
        return Random.nextInt(10) + 1
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdownNow()
        fixExecutor.shutdownNow()
        singleExecutor.shutdownNow()
        scheduleExecutor.shutdownNow()
        cacheExecutor.shutdownNow()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("线程池")
        return super.onCreateOptionsMenu(menu)
    }

}