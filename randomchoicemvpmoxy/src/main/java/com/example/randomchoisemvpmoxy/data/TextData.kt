package com.example.randomchoisemvpmoxy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextData (
    @PrimaryKey
    val text: String
)