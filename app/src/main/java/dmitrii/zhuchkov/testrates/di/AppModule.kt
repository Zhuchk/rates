package dmitrii.zhuchkov.testrates.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dmitrii.zhuchkov.testrates.LaunchActivity

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        NavigationModule::class
    ]
)
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LaunchActivityModule::class])
    fun provideLaunchActivity(): LaunchActivity
}