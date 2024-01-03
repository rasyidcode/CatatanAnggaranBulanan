package com.ipritdev.uangbul.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories"
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val icon: String
) {

    override fun toString(): String = name

}
