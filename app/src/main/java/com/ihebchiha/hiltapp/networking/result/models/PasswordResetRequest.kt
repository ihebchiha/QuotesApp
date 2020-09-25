package com.ihebchiha.hiltapp.networking.result.models
/*
    Status : a boolean that indicates if the request is sent or not
    error: Exception sent by Firebase
*/

data class PasswordResetRequest(
    var status: Boolean,
    var error: Exception?
)