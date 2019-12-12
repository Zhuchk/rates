package dmitrii.zhuchkov.testrates.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitrii.zhuchkov.testrates.features.main.ui.MainFragment
import dmitrii.zhuchkov.testrates.features.ui.SplashFragment

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
@Module
interface LaunchActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun getSplashFragment(): SplashFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun getMainFragment(): MainFragment
}