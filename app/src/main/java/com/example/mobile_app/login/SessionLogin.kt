package com.example.mobile_app.login

data class SessionLogin(
    var userId : Long? = null,
    var email : String? = null,
    var senha : String? = null,
    var token : String? = null,
    var tipo : String? = null
)
