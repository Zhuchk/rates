package dmitrii.zhuchkov.testrates.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitrii.zhuchkov.testrates.di.feature.SplashModule
import dmitry.zhuchkov.feature.main.ui.MainFragment
import dmitry.zhuchkov.feature.splash.ui.SplashFragment

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
@Module
interface LaunchActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    fun getSplashFragment(): SplashFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun getMainFragment(): MainFragment
}