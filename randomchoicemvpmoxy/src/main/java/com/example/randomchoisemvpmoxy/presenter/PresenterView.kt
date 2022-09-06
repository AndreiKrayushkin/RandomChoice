package com.example.randomchoisemvpmoxy.presenter

import android.content.Context
import com.example.randomchoisemvpmoxy.MainViewController
import com.example.randomchoisemvpmoxy.Presenter
import com.example.randomchoisemvpmoxy.data.ControllerData
import com.example.randomchoisemvpmoxy.data.GetData
import com.example.randomchoisemvpmoxy.data.TextData
import com.example.randomchoisemvpmoxy.view.ActivityMVPMoxy
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class PresenterView (private val database: ControllerData) : Presenter, MvpPresenter<MainViewController>() {

    override fun setSendStartData() {
        viewState.setStartData(database.loadData())
    }

    override fun buttonApplyInput(text: String) {
        val text = TextData(text = text)
        database.setData(text)
        viewState.updateDataInView(database.loadData())
    }

    override fun buttonClearList() {
        database.deleteAllList()
        viewState.updateDataInView(database.loadData())
    }

    override fun buttonStartGenerated() {
        val sizeList = database.loadData().size
        if (sizeList != 0) {
            val randomNumber = (0 until sizeList).random()
            val getText = database.loadData()[randomNumber].text
            viewState.showText(getText)
        }
    }

    override fun deleteTextFromList(textObject: TextData) {
        database.deleteThisData(textObject)
        viewState.updateDataInView(database.loadData())
    }
}