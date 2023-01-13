package com.example.randomchoicemvvmdagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomchoicemvvmdagger2.model.GetData
import com.example.randomchoicemvvmdagger2.model.TextData

class MainViewModel(
    private val database: GetData
    ) : ViewModel() {

    private val wordsListLiveData = MutableLiveData<MutableList<TextData>>()
    val wordsList: LiveData<MutableList<TextData>> = wordsListLiveData

    private val singleTextLiveData = MutableLiveData<TextData?>()
    val singleText: LiveData<TextData?> = singleTextLiveData

    init {
        wordsListLiveData.value = database.loadData()
    }

    fun clearList() {
        database.deleteAllList()
        wordsListLiveData.value = database.loadData()
        singleTextLiveData.value = null
    }
    fun startGenerate() {
        if (database.loadData().size > 0) {
            val randomPosition = (0 until database.loadData().size).random()
            if (database.loadData().size > randomPosition) {
                singleTextLiveData.value = database.loadData()[randomPosition]
            }
        }
    }
    fun applyInput(text: String) {
        if (text != "") {
            database.setData(TextData(textId = database.loadData().size, text = text))
            wordsListLiveData.value = database.loadData()
        }
    }
    fun deleteCurrentText(textObject: TextData) {
        database.deleteThisData(textData = textObject)
        wordsListLiveData.value = database.loadData()
    }
}