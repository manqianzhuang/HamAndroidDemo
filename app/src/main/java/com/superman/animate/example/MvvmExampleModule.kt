package com.superman.animate.example

import com.superman.animate.example.di.scope.ActivityScope
import dagger.Binds

@dagger.Module
abstract class MvvmExampleModule {

    @ActivityScope
    @Binds
    abstract fun provideCoroutineActivity(activity: MvvmExampleActivity): MvvmExampleActivity
}