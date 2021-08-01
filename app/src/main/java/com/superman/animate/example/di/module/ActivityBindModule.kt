package com.superman.animate.example.di.module

import com.superman.animate.example.MvvmExampleActivity
import com.superman.animate.example.MvvmExampleModule
import com.superman.animate.example.di.scope.ActivityScope
import dagger.android.ContributesAndroidInjector

@dagger.Module
abstract class ActivityBindModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MvvmExampleModule::class])
    abstract fun contributesCoroutineActivity(): MvvmExampleActivity

}