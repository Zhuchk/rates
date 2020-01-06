package dmitrii.zhuchkov.testrates.navigation.feature

import dmitrii.zhuchkov.testrates.navigation.ToMainScreenEvent
import dmitrii.zhuchkov.component.navigation.NavigationEventProvider
import dmitrii.zhuchkov.feature.splash.navigation.SplashRouter
import javax.inject.Inject

/**
 * Created by Dmitrii on 14.12.2019
 *
 */
class SplashRouterImpl @Inject constructor(
	private val navigationEventProvider: NavigationEventProvider
) : SplashRouter {

	override fun openMainScreen() {
		navigationEventProvider.navigationEvent.value = ToMainScreenEvent()
	}
}