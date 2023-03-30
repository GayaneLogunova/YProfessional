package com.example.task2.models

data class GroupWithParticipants(
    val group: Group,
    val participants: List<Participant>? = null
)
