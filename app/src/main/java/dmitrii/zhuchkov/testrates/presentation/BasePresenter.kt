package dmitrii.zhuchkov.testrates.presentation

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Dmitrii on 11.12.2019
 *
 */
abstract class BasePresenter<T : MVPView> {

    var viewState: T? = null

    val compositeDisposable = CompositeDisposable()

    open fun onCreateView() = Unit

    fun attachView(viewState: MVPView?) {
        this.viewState = viewState as? T
        onCreateView()
    }

    fun disposeOnDestroy() {
        compositeDisposable.clear()
    }
}