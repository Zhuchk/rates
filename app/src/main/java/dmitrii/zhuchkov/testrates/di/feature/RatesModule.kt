package dmitrii.zhuchkov.testrates.di.feature

import dagger.Binds
import dagger.Module
import dmitrii.zhuchkov.testrates.di.FragmentScope
import dmitrii.zhuchkov.testrates.di.network.api.RatesApiModule
import dmitry.zhuchkov.feature.rates.data.datasource.RatesApiDataSource
import dmitry.zhuchkov.feature.rates.data.datasource.RatesApiDataSourceImpl
import dmitry.zhuchkov.feature.rates.data.repository.BaseCurrencyRepositoryImpl
import dmitry.zhuchkov.feature.rates.data.repository.RatesRepositoryImpl
import dmitry.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import dmitry.zhuchkov.feature.rates.domain.repository.RatesRepository

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
@Module(includes = [RatesApiModule::class])
interface RatesModule {

	@Binds
	@FragmentScope
	fun bindRatesRepository(repository: RatesRepositoryImpl): RatesRepository

	@Binds
	@FragmentScope
	fun bindBaseCurrencyRepository(repository: BaseCurrencyRepositoryImpl): BaseCurrencyRepository

	@Binds
	@FragmentScope
	fun bindRatesApiDataSource(dataSource: RatesApiDataSourceImpl): RatesApiDataSource
}