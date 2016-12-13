package be.neodigi.kotlinboilerplate.data.local

import be.neodigi.kotlinboilerplate.data.model.User
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DatabaseHelper @Inject constructor(var realmProvider: Provider<Realm>) {

    fun setUsers(newUsers: Collection<User>): Observable<Collection<User>> {
        return Observable.create(ObservableOnSubscribe<Collection<User>> { e ->
            if (e.isDisposed) return@ObservableOnSubscribe

            realmProvider.get().use { realm ->
                realm.executeTransaction {
                    realm.copyToRealmOrUpdate(newUsers)
                    e.onNext(newUsers)
                }
            }
        })
    }
}