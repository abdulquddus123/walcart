package com.example.walcartapp.repository

import com.example.walcartapp.model.Category
import com.example.walcartapp.network.ApiInterface
import dagger.hilt.android.scopes.ActivityScoped
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class CategoryRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getCategory(body: JSONObject): Response<Category> {
        val response = apiInterface.getCategories(body.toString())
        return response
    }
}
