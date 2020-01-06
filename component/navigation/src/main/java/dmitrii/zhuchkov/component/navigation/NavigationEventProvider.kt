package dmitrii.zhuchkov.component.navigation

import javax.inject.Inject

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
interface NavigationEventProvider {

	val navigationEvent: SingleLiveEvent<NavigationEvent>
}

class NavigationEventProviderImpl @Inject constructor() : NavigationEventProvider {

	override val navigationEvent: SingleLiveEvent<NavigationEvent> = SingleLiveEvent()
}