package com.example.randomchoisemvpmoxy

import android.content.Context
import com.example.randomchoisemvpmoxy.data.TextData
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainViewController :MvpView {
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
