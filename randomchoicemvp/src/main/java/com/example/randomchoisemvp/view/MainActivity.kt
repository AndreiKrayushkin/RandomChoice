package com.example.randomchoisemvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.randomchoisemvp.R
import com.example.randomchoisemvp.MainViewController
import com.example.randomchoisemvp.Presenter
import com.example.randomchoisemvp.presenter.PresenterView
import com.example.randomchoisemvp.data.TextData
import com.example.randomchoisemvp.databinding.ActivityMvpBinding

class MainActivity : AppCompatActivity(), MainViewController, OnTextClickListener {
    private lateinit var binding: ActivityMvpBinding
    private lateinit var presenter: Presenter
    private lateinit var mainAdapter: TextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = PresenterView(this)

        presenter.setSendStartData()

        visibleTextInfo()

        binding.buttonApplyInput.setOnClickListener {
            if (binding.inputText.text.isNotEmpty()) {
                presenter.buttonApplyInput(binding.inputText.text.toString())
            }
        }

        binding.buttonClearList.setOnClickListener {
            presenter.buttonClearList()
        }

        binding.buttonStartGenerated.setOnClickListener {
            presenter.buttonStartGenerated()
        }
    }

    override fun setStartData(list: MutableList<TextData>) {
        mainAdapter = TextAdapter(list, this)
        setDataInRecyclerView()
    }

    override fun showText(data: String) {
        binding.choiceText.text = data
    }

    override fun updateDataInView(list: MutableList<TextData>) {
        clearEditText()
        setStartTitleText()
        updateAdapter(list)
        visibleTextInfo()
    }

    private fun setDataInRecyclerView() {
        binding.recyclerListChoice.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter
        }
    }

    private fun clearEditText() {
        binding.inputText.text.clear()
    }

    private fun setStartTitleText() {
        binding.choiceText.text = getText(R.string.start_title)
    }

    private fun updateAdapter(list: MutableList<TextData>) {
        mainAdapter.textList = list
        mainAdapter.notifyDataSetChanged()
    }

    private fun visibleTextInfo() {
        if (mainAdapter.textList.size != 0) {
            binding.bgTextList.visibility = android.view.View.INVISIBLE
        } else {
            binding.bgTextList.visibility = android.view.View.VISIBLE
        }
    }

    override fun invoke(textObject: TextData, position: Int) {
        presenter.deleteTextFromList(textObject)
    }
}