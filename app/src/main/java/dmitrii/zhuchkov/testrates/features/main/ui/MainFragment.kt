package dmitrii.zhuchkov.testrates.features.main.ui

import dmitrii.zhuchkov.testrates.R
import dmitrii.zhuchkov.testrates.base.BaseFragment
import dmitrii.zhuchkov.testrates.features.main.presentation.MainPresenter
import dmitrii.zhuchkov.testrates.features.main.presentation.MainView
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class MainFragment : BaseFragment(), MainView {

    override val layoutRes: Int = R.layout.main_fragment

    @Inject
    lateinit var presenter: MainPresenter
}