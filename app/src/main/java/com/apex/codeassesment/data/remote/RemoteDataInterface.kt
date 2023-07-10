package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.utils.Response
import retrofit2.http.GET

interface RemoteDataInterface {

    @GET("api?results=10")
    suspend fun makeApiToGetRandomUsers(): Response<List<User>>?

    @GET("api")
    suspend fun makeApiToGetUser(): Response<User>?

}
