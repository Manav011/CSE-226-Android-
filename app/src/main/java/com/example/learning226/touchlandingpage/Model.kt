package com.example.learning226.touchlandingpage

data class TimetableItem(
    val subject: String,
    val code: String,
    val time: String,
    val icon: Int
)

data class GridItem(
    val title: String,
    val count: Any,
    val icon: Int
)
