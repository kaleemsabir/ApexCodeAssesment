package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.utils.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


// TODO (2 points): Add tests
class RemoteDataSource @Inject constructor() : RemoteDataInterface {

    // TODO (5 points): Load data from endpoint https://randomuser.me/api
//  fun LoadUser() = User.createRandom()

    // TODO (3 points): Load data from endpoint https://randomuser.me/api?results=10
    // TODO (Optional Bonus: 3 points): Handle succes and failure from endpoints
//  fun loadUsers() = (0..10).map { User.createRandom() }\


    private val BASE_URL = "https://api.example.com/"
    private lateinit var retrofit: Retrofit

    init {
        RemoteDataInterfaceImpl()
    }

    fun RemoteDataInterfaceImpl() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override suspend fun makeApiToGetRandomUsers(): Response<List<User>>? {
        return null
    }

    override suspend fun makeApiToGetUser(): Response<User>? {
        return null
    }

}
