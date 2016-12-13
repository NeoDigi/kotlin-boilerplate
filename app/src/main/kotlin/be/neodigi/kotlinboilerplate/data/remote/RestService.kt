package be.neodigi.kotlinboilerplate.data.remote

import be.neodigi.kotlinboilerplate.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {

    @GET("users")
    fun getUsers(): Observable<List<User>>
}