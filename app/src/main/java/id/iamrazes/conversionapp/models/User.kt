package id.iamrazes.conversionapp.models

data class User(
    var name : String? = null,
    var username : String? = null,
    var password : String? = null
)

var dataDummy = listOf(
    User (
        name = "Admin",
        username = "admin",
        password = "admin"
    )
)