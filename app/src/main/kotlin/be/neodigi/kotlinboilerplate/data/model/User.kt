package be.neodigi.kotlinboilerplate.data.model

import io.realm.RealmObject

open class User(
        open var name: String = "",
        open var username: String = "",
        open var email: String = "",
        open var phone: String = "",
        open var website: String = ""
) : RealmObject()