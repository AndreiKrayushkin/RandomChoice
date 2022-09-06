package com.example.randomchoicemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.randomchoicemvvm.databinding.ActivityMainBinding
import com.example.randomchoicemvvm.model.TextData

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var textAdapter: TextAdapter? = null
    private var onTextClick: OnTextClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)

        viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

        initialClickOnText()
        initialAdapter()
        observableOnCurrentText()

        bindingMain.buttonApplyInput.setOnClickListener {
            viewModel.applyInput(bindingMain.inputText.text.toString())
            clearEditText()
        }

        bindingMain.buttonClearList.setOnClickListener {
            viewModel.clearList()
        }

        bindingMain.buttonStartGenerated.setOnClickListener {
            viewModel.startGenerate()
        }
    }

    private fun clearEditText() {
        bindingMain.inputText.text.clear()
    }

    private fun initialClickOnText() {
        onTextClick = object : OnTextClickListener {
            override fun invoke(textObject: TextData, position: Int) {
                viewModel.deleteCurrentText(textObject)
            }
        }
    }

    private fun initialAdapter() {
        viewModel.wordsList.observe(this) {
            textAdapter = TextAdapter(it, onTextClick)
            bindingMain.recyclerListChoice.apply {
                layoutManager = GridLayoutManager(bindingMain.root.context, 2)
                adapter = textAdapter
            }

            setSubInfo(it.size)
        }
    }

    private fun setSubInfo(size: Int) {
        when (size) {
            0 -> {
                visibleSubInfo(visible = View.VISIBLE)
            }
            else -> {
                visibleSubInfo(visible = View.INVISIBLE)
            }
        }

    }

    private fun visibleSubInfo(visible: Int) {
        with(bindingMain) {
            bgTextList.visibility = visible
        }
    }

    private fun observableOnCurrentText() {
        viewModel.singleText.observe(this) {
            with(bindingMain) {
                choiceText.text =
                    it?.text ?: getText(R.string.start_title)
            }
        }
    }
}
