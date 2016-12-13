package be.neodigi.kotlinboilerplate.ui.main

import android.support.annotation.CallSuper
import be.neodigi.kotlinboilerplate.data.DataManager
import be.neodigi.kotlinboilerplate.injection.ConfigPersistent
import be.neodigi.kotlinboilerplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject constructor(val dataManager: DataManager): BasePresenter<MainMvpView>()  {

    lateinit var disposable: Disposable

    @CallSuper
    override fun attachView(mvpView: MainMvpView) {
        super.attachView(mvpView)
    }

    @CallSuper
    override fun detachView() {
        disposable.dispose()
        super.detachView()
    }

    fun loadUsers() {
        checkViewAttached()
        disposable = dataManager.syncUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                { users ->
                    if (users.isEmpty()) {
                        getMvpView()?.showUsersEmpty()
                    } else {
                        getMvpView()?.showUsers(users)
                    }
                },
                { error ->
                    Timber.e(error, "There was an error loading the users.")
                    getMvpView()?.showError()
                },
                {
                    Timber.d("OnCompleted")
                }
        )
    }
}