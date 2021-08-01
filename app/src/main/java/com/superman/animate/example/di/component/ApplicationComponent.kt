package com.superman.animate.example.di.component

import android.app.Application
import com.superman.animate.BaseApplication
import com.superman.animate.example.di.module.ActivityBindModule
import com.superman.animate.example.di.module.HttpModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        HttpModule::class,
        ActivityBindModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    override fun inject(instance: BaseApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}