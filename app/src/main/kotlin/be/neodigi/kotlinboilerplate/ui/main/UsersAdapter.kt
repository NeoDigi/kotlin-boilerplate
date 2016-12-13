package be.neodigi.kotlinboilerplate.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import be.neodigi.kotlinboilerplate.R
import be.neodigi.kotlinboilerplate.data.model.User
import javax.inject.Inject
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter @Inject constructor(): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var users: Collection<User>

    init {
        users = emptyList()
    }

    fun setUsers(users: Collection<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users.elementAt(position)
        holder.itemView.textName.text = String.format("%s %s", user.username, user.name)
        holder.itemView.textEmail.text = user.email
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

