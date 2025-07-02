package com.example.codetest.domain.model

import java.io.Serializable

data class Animal(
    val name: String = "",
    val location: String = "",
    val image: String = ""
) : Serializable
