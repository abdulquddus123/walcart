package com.example.walcartapp.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("data") var data: Data? = Data()
)

data class Parent(
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("bnName") var bnName: String? = null
)

data class Image(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("signedUrl") var signedUrl: String? = null
)

data class Categories(
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("bnName") var bnName: String? = null,
    @SerializedName("parent") var parent: Parent? = Parent(),
    // @SerializedName("parents"         ) var parents         : ArrayList<String>? = arrayListOf(),
    @SerializedName("image") var image: Image? = Image(),
    @SerializedName("attributeSetUid") var attributeSetUid: String? = null,
    @SerializedName("isActive") var isActive: Boolean? = null,
    @SerializedName("inActiveNote") var inActiveNote: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null
)

data class Result(
    @SerializedName("categories") var categories: ArrayList<Categories> = arrayListOf()
)

data class GetCategories(
    @SerializedName("result") var result: Result? = Result()
)

data class Data(
    @SerializedName("getCategories") var getCategories: GetCategories? = GetCategories()

)