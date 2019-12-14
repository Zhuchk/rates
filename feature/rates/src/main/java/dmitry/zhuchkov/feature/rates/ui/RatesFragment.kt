package dmitry.zhuchkov.feature.rates.ui

import dmitry.zhuchkov.component.mvp.MVPFragment
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.presentation.RatesPresenter
import dmitry.zhuchkov.feature.rates.presentation.RatesView
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class RatesFragment : MVPFragment(), RatesView {

    override val layoutRes: Int = R.layout.rates_fragment

    @Inject
    lateinit var presenter: RatesPresenter
}