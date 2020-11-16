package me.ray.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class SearchData(
    val `data`: Data
)

data class Data(
    val apiSearchV5: ApiSearchV5
)

@Entity(tableName = "search")
data class ApiSearchV5(
    val results: List<Result>,
    val total: Int,
    @PrimaryKey
    val key:Int
)

data class Result(
    val User: User,
    val id: String,
    val key: String,
    val name: String,
    val slugifiedName: String,
    val thumbnailSmall: String,
    val title: String,
    val visibility: String
): Serializable

data class User(
    val id: Int,
    val username: String
)