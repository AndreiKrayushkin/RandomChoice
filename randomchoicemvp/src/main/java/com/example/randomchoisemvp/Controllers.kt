package com.example.randomchoisemvp

import com.example.randomchoisemvp.data.TextData

interface MainViewController {
    fun setStartData(list: MutableList<TextData>)
    fun showText(data: String)
    fun updateDataInView(list: MutableList<TextData>)
}
interface Presenter {
    fun setSendStartData()
    fun buttonApplyInput(text: String)
    fun buttonClearList()
    fun buttonStartGenerated()
    fun deleteTextFromList(textObject: TextData)
}
