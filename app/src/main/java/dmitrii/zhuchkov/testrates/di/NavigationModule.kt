package dmitrii.zhuchkov.testrates.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dmitrii.zhuchkov.component.navigation.NavigationEventProvider
import dmitrii.zhuchkov.component.navigation.NavigationEventProviderImpl
import dmitrii.zhuchkov.component.navigation.Router
import dmitrii.zhuchkov.testrates.navigation.RouterImpl

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
@Module
abstract class NavigationModule {

	@Module
	companion object {

		@Provides
		@JvmStatic
		fun provideRouter(provider: NavigationEventProvider): Router =
			RouterImpl(provider)
	}

	@Binds
	@AppScope
	abstract fun bindNavigationActionProvider(provider: NavigationEventProviderImpl): NavigationEventProvider
}