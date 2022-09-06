package com.example.randomchoisemvp.data

interface ControllerData {
    fun loadData(): MutableList<TextData>
    fun getSingleText(textName: String): TextData
    fun setData(textData: TextData)
    fun deleteAllList()
    fun deleteThisData(textData: TextData)
}