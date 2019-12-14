package dmitrii.zhuchkov.testrates.features.ui

import dmitrii.zhuchkov.testrates.R
import dmitry.zhuchkov.component.mvp.MVPFragment
import dmitrii.zhuchkov.testrates.features.presentation.SplashPresenter
import dmitrii.zhuchkov.testrates.features.presentation.SplashView
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