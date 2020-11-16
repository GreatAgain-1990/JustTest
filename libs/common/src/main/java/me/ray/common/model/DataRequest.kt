package me.ray.common.model

import java.io.Serializable

/**
 * 请求参数
 * {
"operationName": "SearchBarByTerm",
"variables": {
"searchArguments": {
"sortBy": "installsAllTime",
"size": 5,
"offset": 0,
"page": 1,
"term": "abc",
"privateApisJwt": ""
}
},
"query": "\n        query SearchBarByTerm($searchArguments: SearchArguments) {\n          apiSearchV5(searchArguments: $searchArguments) {\n            results {\n              id\n              name\n              title\n              thumbnailSmall\n              key\n              visibility\n              slugifiedName\n              User {\n                id\n                username\n              }\n            }\n            total\n          }\n        }\n      "
}
 */

data class RequestBean(
    val operationName: String="SearchBarByTerm",
    val query: String="\n        query SearchBarByTerm(\$searchArguments: SearchArguments) {\n          apiSearchV5(searchArguments: \$searchArguments) {\n            results {\n              id\n              name\n              title\n              thumbnailSmall\n              key\n              visibility\n              slugifiedName\n              User {\n                id\n                username\n              }\n            }\n            total\n          }\n        }\n      "
    ,
    val variables: Variables
):Serializable

data class Variables(
    val searchArguments: SearchArguments
):Serializable

data class SearchArguments(
    val offset: Int=0,
    val page: Int,
    val privateApisJwt: String="",
    val size: Int=10,
    val sortBy: String="installsAllTime",
    val term: String
):Serializable
