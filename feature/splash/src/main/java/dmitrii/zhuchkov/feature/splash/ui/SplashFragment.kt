package dmitrii.zhuchkov.feature.splash.ui

import dmitrii.zhuchkov.component.mvp.MVPFragment
import dmitrii.zhuchkov.feature.splash.R
import dmitrii.zhuchkov.feature.splash.presentation.SplashPresenter
import dmitrii.zhuchkov.feature.splash.presentation.SplashView
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class SplashFragment : MVPFragment(), SplashView {

	override val layoutRes: Int = R.layout.splash_fragment

	@Inject
	lateinit var presenter: SplashPresenter
}