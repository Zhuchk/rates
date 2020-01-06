package dmitrii.zhuchkov.testrates.di.data

import dagger.Binds
import dagger.Module
import dmitrii.zhuchkov.testrates.di.AppScope
import dmitrii.zhuchkov.feature.rates.data.datasource.BaseCurrencyDataSource
import dmitrii.zhuchkov.feature.rates.data.datasource.BaseCurrencyDataSourceImpl

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
@Module
interface DataModule {

	@Binds
	@AppScope
	fun bindBaseCurrencyDataSource(dataSource: BaseCurrencyDataSourceImpl): BaseCurrencyDataSource
}