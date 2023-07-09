package com.example.ezo.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("items")
    val items: List<ItemData>,
    @SerializedName("status")
    val status: String
)

data class ItemData(
    @SerializedName("itemBarcode")
    val itemBarcode: String,
    @SerializedName("itemName")
    val itemName: String,
    @SerializedName("itemPrice")
    val itemPrice: String,
    @SerializedName("url")
    val url: String
)