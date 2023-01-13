package com.example.randomchoice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomchoice.databinding.ActivityMainBinding
//test commit
class MainActivity : AppCompatActivity(), OnTextClickListener {
    private lateinit var textAdapter: TextAdapter
    private lateinit var textDao: TextDatabase

    private lateinit var bindingMain: ActivityMainBinding

    private var textList: MutableList<TextData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("TAG", "onCreate")
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)

        textDao = TextDatabase.getDatabase(this)

        textAdapter = TextAdapter(textDao.getTextDao().getTextList(), this)

        listenerSizeListForTextInfoVisible()
        setDataInRecyclerView()

        bindingMain.buttonApplyInput.setOnClickListener {
            if (bindingMain.inputText.text.isNotEmpty()) {
                val text = createTextObject()
                textDao.getTextDao().insert(text)
                bindingMain.inputText.text.clear()
                setNewListInAdapter()
                textAdapter.notifyItemChanged(textDao.getTextDao().getTextList().size-1)
                listenerSizeListForTextInfoVisible()
            }
        }

        bindingMain.buttonClearList.setOnClickListener {
            textDao.getTextDao().deleteAllList()
            bindingMain.choiceText.text = ""
            setNewListInAdapter()
            textAdapter.notifyDataSetChanged()
            listenerSizeListForTextInfoVisible()
            bindingMain.choiceText.text = getText(R.string.start_title)
        }

        bindingMain.buttonStartGenerated.setOnClickListener {
            textList.clear()
            textList.addAll(textDao.getTextDao().getTextList())
            if (textList.isNotEmpty()) {
                val sizeList = textList.size
                val randomNumber = (0 until sizeList).random()
                bindingMain.choiceText.text = textList[randomNumber].text
            }
        }
    }

    private fun setDataInRecyclerView() {
        bindingMain.recyclerListChoice.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
            adapter = textAdapter
        }
    }

    private fun listenerSizeListForTextInfoVisible() {
        if (textDao.getTextDao().getTextList().isNotEmpty()) {
            bindingMain.bgTextList.visibility = View.INVISIBLE
        } else {
            bindingMain.bgTextList.visibility = View.VISIBLE
        }
    }

    private fun createTextObject() =
        TextData(text = bindingMain.inputText.text.toString())

    private fun setNewListInAdapter() {
        textAdapter.textList = textDao.getTextDao().getTextList()
    }

    override fun invoke(textObject: TextData, position: Int) {
        textDao.getTextDao().delete(textObject)
        setNewListInAdapter()
        textAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        Log.v("TAG", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.v("TAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("TAG", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("TAG", "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.v("TAG", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("TAG", "onRestart")
    }
}