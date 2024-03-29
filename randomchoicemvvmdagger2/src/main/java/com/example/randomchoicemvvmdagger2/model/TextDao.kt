package com.example.randomchoicemvvmdagger2.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface TextDao {
    @Query("SELECT * FROM textData")
    fun getTextList(): MutableList<TextData>

    @Query("SELECT * FROM textData WHERE textId = :id")
    fun getText(id: Int): TextData

    @Query("DELETE FROM textData")
    fun deleteAllList()

    @Insert
    fun insert(textData: TextData)

    @Update
    fun update(textData: TextData)

    @Delete
    fun delete(textData: TextData)
}