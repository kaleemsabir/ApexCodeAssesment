package com.apex.codeassesment.data

import com.apex.codeassesment.data.local.localdatasource
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.data.remote.RemoteDataInterface
import com.apex.codeassesment.utils.Response
import javax.inject.Inject

// TODO (2 points) : Add tests
// TODO (3 points) : Hide this class through an interface, inject the interface in the clients instead and remove warnings
class UserRepository @Inject constructor(
    private val localDataSource: localdatasource,
    private val remoteDataInterface: RemoteDataInterface
) {

    suspend fun makeApiToGetRandomUsers(): Response<List<User>> {
        return remoteDataInterface.makeApiToGetRandomUsers()
    }

    suspend fun makeApiToGetUser(): Response<User> {
        return remoteDataInterface.makeApiToGetUser()
    }
}
