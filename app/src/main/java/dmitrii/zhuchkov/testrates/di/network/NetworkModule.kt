package dmitrii.zhuchkov.testrates.di.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dmitrii.zhuchkov.testrates.R
import dmitrii.zhuchkov.testrates.di.AppScope
import dmitrii.zhuchkov.testrates.di.network.qualifiers.RatesApiQualifier
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
@Module
class NetworkModule {

	@Provides
	@AppScope
	fun provideOkHttpClient(): OkHttpClient =
		OkHttpClient.Builder()
			.build()

	@Provides
	@AppScope
	@RatesApiQualifier
	fun provideRatesRetrofit(okHttpClient: OkHttpClient, context: Context): Retrofit =
		createRetrofit(context.getString(R.string.rates_base_url), okHttpClient)

	private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient) =
		Retrofit.Builder()
			.baseUrl(baseUrl)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
}