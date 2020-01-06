package dmitrii.zhuchkov.testrates.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitrii.zhuchkov.testrates.di.feature.RatesModule
import dmitrii.zhuchkov.testrates.di.feature.SplashModule
import dmitrii.zhuchkov.feature.rates.ui.RatesFragment
import dmitrii.zhuchkov.feature.splash.ui.SplashFragment

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
@Module
interface LaunchActivityModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [SplashModule::class])
	fun provideSplashFragment(): SplashFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [RatesModule::class])
	fun provideRatesFragment(): RatesFragment
}