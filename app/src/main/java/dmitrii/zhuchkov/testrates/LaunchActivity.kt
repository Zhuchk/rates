package dmitrii.zhuchkov.testrates

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import dagger.android.DispatchingAndroidInjector
import dmitrii.zhuchkov.component.navigation.NavigationEvent
import dmitrii.zhuchkov.component.navigation.NavigationEventProvider
import dmitrii.zhuchkov.testrates.navigation.NavigationExit
import dmitrii.zhuchkov.testrates.navigation.NavigationUp
import dmitrii.zhuchkov.component.mvp.MVPFragment
import dmitrii.zhuchkov.component.ui.BaseActivity
import dmitrii.zhuchkov.component.ui.hideSoftKeyboard
import javax.inject.Inject

class LaunchActivity : BaseActivity() {

	@Inject
	lateinit var navigationEventProvider: NavigationEventProvider

	@Inject
	override lateinit var androidInjector: DispatchingAndroidInjector<Any>

	override var contentLayout = R.layout.launch_activity

	private val navController: NavController
		get() = findNavController(R.id.navigation_host)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		navigationEventProvider.navigationEvent.observe(this, Observer(this::navigate))

		navController.addOnDestinationChangedListener { _, _, _ ->
			hideSoftKeyboard()
		}
	}

	private fun navigate(event: NavigationEvent) {
		when (event) {
			is NavigationExit -> finish()
			is NavigationUp   -> navController.navigateUp()
			is NavDirections  -> navController.navigate(event)

			else              -> throw IllegalArgumentException("unknown navigation event")
		}
	}

	override fun onBackPressed() {
		val fragment = supportFragmentManager.findFragmentById(R.id.navigation_host)

		fragment?.childFragmentManager?.fragments?.forEach {
			if (it is MVPFragment && !it.onBackPressed()) {
				super.onBackPressed()
			}
		}
	}
}