package com.example.walcartapp.viewModel

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walcartapp.model.Category
import com.example.walcartapp.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRespository: CategoryRepository
) : ViewModel() {

    private var _getCategoryData: MutableLiveData<Category> = MutableLiveData<Category>()
    var getCategoryData: LiveData<Category> = _getCategoryData
    var isLoading= mutableStateOf(false)





    fun getCategoryData() {
        val paramObject = JSONObject()
        paramObject.put(
            "query", "{\n" +
                    "  getCategories(pagination: { limit: 100, skip: 0 }) {\n" +
                    "    result {\n" +
                    "      categories {\n" +
                    "        uid\n" +
                    "        enName\n" +
                    "        bnName\n" +
                    "        parent {\n" +
                    "          uid\n" +
                    "          enName\n" +
                    "          bnName\n" +
                    "        }\n" +
                    "        parents {\n" +
                    "          uid\n" +
                    "          enName\n" +
                    "          bnName\n" +
                    "        }\n" +
                    "        image {\n" +
                    "          name\n" +
                    "          url\n" +
                    "          signedUrl\n" +
                    "        }\n" +
                    "        attributeSetUid\n" +
                    "        isActive\n" +
                    "        inActiveNote\n" +
                    "        createdAt\n" +
                    "        updatedAt\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}"
        )
        GlobalScope.launch {
            try {

                val result = categoryRespository.getCategory(paramObject)
                if(result.isSuccessful){
                    isLoading.value=true
                //    _getCategoryData.value=result.body()
                    _getCategoryData.postValue(result.body())
                }

                Log.e(
                    "response",
                    result.body()?.data?.getCategories?.result?.categories?.get(0)?.bnName.toString()
                )
            } catch (e: java.lang.Exception) {
                Log.e("response", e.toString())
                e.printStackTrace()
            }
        }
    }
}