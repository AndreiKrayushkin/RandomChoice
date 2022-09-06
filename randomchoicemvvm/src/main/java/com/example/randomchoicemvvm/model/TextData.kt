package com.example.randomchoicemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextData (
    @PrimaryKey
    val textId: Int,
    val text: String
    )