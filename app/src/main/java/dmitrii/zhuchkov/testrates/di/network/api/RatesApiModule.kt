package dmitrii.zhuchkov.testrates.di.network.api

import dagger.Module
import dagger.Provides
import dmitrii.zhuchkov.testrates.di.FragmentScope
import dmitrii.zhuchkov.testrates.di.network.qualifiers.RatesApiQualifier
import dmitry.zhuchkov.feature.rates.data.datasource.RatesApi
import retrofit2.Retrofit

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
@Module
class RatesApiModule {

    @Provides
    @FragmentScope
    fun provideRatesApi(@RatesApiQualifier retrofit: Retrofit): RatesApi =
        retrofit.create(RatesApi::class.java)
}