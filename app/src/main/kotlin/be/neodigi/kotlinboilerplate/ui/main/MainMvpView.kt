package be.neodigi.kotlinboilerplate.ui.main

import be.neodigi.kotlinboilerplate.data.model.User
import be.neodigi.kotlinboilerplate.ui.base.MvpView

interface MainMvpView : MvpView {

    fun showUsers(users: Collection<User>)

    fun showUsersEmpty()

    fun showError()
}