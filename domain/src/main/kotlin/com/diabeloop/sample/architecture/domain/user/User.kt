package com.diabeloop.sample.architecture.domain.user

data class User(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val diabetesType: DiabetesType? = null
)
