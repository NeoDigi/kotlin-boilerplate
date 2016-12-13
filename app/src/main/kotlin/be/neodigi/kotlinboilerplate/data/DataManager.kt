package be.neodigi.kotlinboilerplate.data

import be.neodigi.kotlinboilerplate.data.local.DatabaseHelper
import be.neodigi.kotlinboilerplate.data.local.PreferencesHelper
import be.neodigi.kotlinboilerplate.data.model.User
import be.neodigi.kotlinboilerplate.data.remote.RestService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(val databaseHelper: DatabaseHelper,
                                      val restService: RestService,
                                      val preferencesHelper: PreferencesHelper) {

    fun syncUsers(): Observable<Collection<User>> {
        return restService.getUsers().concatMap { users ->
            databaseHelper.setUsers(users)
        }
    }
}