package dmitrii.zhuchkov.testrates.di.feature

import dagger.Binds
import dagger.Module
import dmitrii.zhuchkov.testrates.di.FragmentScope
import dmitrii.zhuchkov.testrates.di.network.api.RatesApiModule
import dmitrii.zhuchkov.feature.rates.data.datasource.RatesApiDataSource
import dmitrii.zhuchkov.feature.rates.data.datasource.RatesApiDataSourceImpl
import dmitrii.zhuchkov.feature.rates.data.repository.BaseCurrencyRepositoryImpl
import dmitrii.zhuchkov.feature.rates.data.repository.RatesRepositoryImpl
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import dmitrii.zhuchkov.feature.rates.domain.repository.RatesRepository

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