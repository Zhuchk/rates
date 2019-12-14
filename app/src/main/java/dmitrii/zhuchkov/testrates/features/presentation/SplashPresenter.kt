package dmitrii.zhuchkov.testrates.features.presentation

import dmitry.zhuchkov.component.navigation.NavigationEventProvider
import dmitrii.zhuchkov.testrates.navigation.ToMainScreenEvent
import dmitry.zhuchkov.component.mvp.MVPPresenter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class SplashPresenter @Inject constructor(
    private val navigationEventProvider: NavigationEventProvider
) : MVPPresenter<SplashView>() {

    private companion object {
        const val DELAY_SEC = 1L
    }

    override fun onCreateView() {
        Completable.timer(DELAY_SEC, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                navigationEventProvider.navigationEvent.value = ToMainScreenEvent()
            }
            .addTo(compositeDisposable)
    }
}