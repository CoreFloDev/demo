package io.coreflodev.dog.common.repo.network

interface DogApi {
    suspend fun getDogList(page: Int = 0): List<Dog>
    suspend fun getDog(id: String): Dog
}
