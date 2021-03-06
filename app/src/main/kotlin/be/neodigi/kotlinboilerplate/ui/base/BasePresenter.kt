package be.neodigi.kotlinboilerplate.ui.base

abstract class BasePresenter<T : MvpView> : Presenter<T> {

    private var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    fun isViewAttached(): Boolean {
        return mvpView != null
    }

    fun getMvpView(): T? {
        return mvpView
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException :
            RuntimeException("Please call Presenter.attachView(MvpView) before"
                    + " requesting data to the Presenter")
}