package dmitry.zhuchkov.feature.main.ui

import dmitry.zhuchkov.component.mvp.MVPFragment
import dmitry.zhuchkov.feature.main.R
import dmitry.zhuchkov.feature.main.presentation.MainPresenter
import dmitry.zhuchkov.feature.main.presentation.MainView
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class MainFragment : MVPFragment(), MainView {

    override val layoutRes: Int = R.layout.main_fragment

    @Inject
    lateinit var presenter: MainPresenter
}