package com.example.randomchoicemvvmdagger2.model

import android.content.Context

class GetData(context: Context) {
    private var textDao = TextDatabase.getDatabase(context)

    fun loadData(): MutableList<TextData> {
        return textDao.getTextDao().getTextList()
    }

    fun getSingleText(textId: Int): TextData {
        return textDao.getTextDao().getText(textId)
    }

    fun setData(textData: TextData) {
        textDao.getTextDao().insert(textData)
    }

    fun deleteAllList() {
        textDao.getTextDao().deleteAllList()
    }

    fun deleteThisData(textData: TextData) {
        textDao.getTextDao().delete(textData)
    }
}