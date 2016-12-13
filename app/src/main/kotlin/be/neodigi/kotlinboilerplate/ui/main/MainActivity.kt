package be.neodigi.kotlinboilerplate.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import be.neodigi.kotlinboilerplate.R
import be.neodigi.kotlinboilerplate.data.model.User
import be.neodigi.kotlinboilerplate.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {

    @Inject lateinit var mainPresenter: MainPresenter
    @Inject lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = usersAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainPresenter.attachView(this)
        mainPresenter.loadUsers()
    }

    override fun onDestroy() {
        mainPresenter.detachView()
        super.onDestroy()
    }

    /***** MVP View methods implementation *****/

    override fun showUsers(users: Collection<User>) {
        usersAdapter.setUsers(users)
    }

    override fun showUsersEmpty() {
        usersAdapter.setUsers(emptyList())
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun showError() {
        throw UnsupportedOperationException("not implemented")
    }
}
