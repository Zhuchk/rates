package dmitrii.zhuchkov.testrates.navigation

import dmitrii.zhuchkov.component.navigation.NavigationEvent
import dmitrii.zhuchkov.component.navigation.NavigationEventProvider
import dmitrii.zhuchkov.component.navigation.Router

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
class RouterImpl(
	private val provider: NavigationEventProvider
) : Router {

	override fun navigate(event: NavigationEvent) {
		provider.navigationEvent.value = event
	}
}