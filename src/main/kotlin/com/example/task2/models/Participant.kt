package com.example.task2.models

data class Participant(
    val id: Int,
    val name: String,
    val wish: String,
    val recipient: Participant? = null
)
