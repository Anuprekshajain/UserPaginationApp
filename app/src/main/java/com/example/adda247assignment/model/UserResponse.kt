package com.example.adda247assignment.model

data class UserResponse(
    val code: Int,
    val data: List<Data>,
    val meta: Meta
)