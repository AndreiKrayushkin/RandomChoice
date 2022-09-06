package com.example.randomchoisemvp.presenter

import com.example.randomchoisemvp.Presenter
import com.example.randomchoisemvp.data.ControllerData
import com.example.randomchoisemvp.data.GetData
import com.example.randomchoisemvp.data.TextData
import com.example.randomchoisemvp.view.MainActivity

class PresenterView(
    private var view: MainActivity,
    private var database: ControllerData = GetData(view.applicationContext)
) : Presenter {
    override fun setSendStartData() {
        view.setStartData(database.loadData())
    }

    override fun buttonApplyInput(text: String) {
        val text = TextData(text = text)
        database.setData(text)
        view.updateDataInView(database.loadData())
    }

    override fun buttonClearList() {
        database.deleteAllList()
        view.updateDataInView(database.loadData())
    }

    override fun buttonStartGenerated() {
        val sizeList = database.loadData().size
        if (sizeList != 0) {
            val randomNumber = (0 until sizeList).random()
            val getText = database.loadData()[randomNumber].text
            view.showText(getText)
        }
    }

    override fun deleteTextFromList(textObject: TextData) {
        database.deleteThisData(textObject)
        view.updateDataInView(database.loadData())
    }

    fun getSum(sum1: Int, sum2: Int) = sum1 + sum2
}