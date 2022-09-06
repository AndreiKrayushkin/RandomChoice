package com.example.randomchoisemvp.data

import android.content.Context

class GetData (context: Context) : ControllerData {
    private var textDao = TextDatabase.getDatabase(context)

    override fun loadData(): MutableList<TextData> {
        return textDao.getTextDao().getTextList()
    }

    override fun getSingleText(textName: String): TextData {
        return textDao.getTextDao().getText(textName)
    }

    override fun setData(textData: TextData) {
        textDao.getTextDao().insert(textData)
    }

    override fun deleteAllList() {
        textDao.getTextDao().deleteAllList()
    }

    override fun deleteThisData(textData: TextData) {
        textDao.getTextDao().delete(textData)
    }
}