package com.example.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>

)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String

) {
    fun toMenuItemRoom(): MenuItemRoom = MenuItemRoom(this.id, this.title, this.description, this.price.toDouble(), this.image, this.category)
}