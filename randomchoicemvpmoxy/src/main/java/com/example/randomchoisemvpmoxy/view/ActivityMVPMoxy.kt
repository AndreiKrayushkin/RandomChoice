package com.example.randomchoisemvpmoxy.view

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.randomchoisemvpmoxy.MainViewController
import com.example.randomchoisemvpmoxy.R
import com.example.randomchoisemvpmoxy.data.GetData
import com.example.randomchoisemvpmoxy.data.TextData
import com.example.randomchoisemvpmoxy.databinding.ActivityMvpmoxyBinding
import com.example.randomchoisemvpmoxy.presenter.PresenterView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import moxy.viewstate.strategy.alias.AddToEnd

class ActivityMVPMoxy : MvpAppCompatActivity(), MainViewController, OnTextClickListener {
    private lateinit var binding: ActivityMvpmoxyBinding
    @InjectPresenter
    lateinit var presenter: PresenterView

    @ProvidePresenter
    fun provideAddDataPresenter(): PresenterView {
        return PresenterView(GetData(this))
    }

    private var mainAdapter: TextAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvpmoxyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter.setSendStartData()

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

    @AddToEnd
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
        mainAdapter?.textList = list
        mainAdapter?.notifyDataSetChanged()
    }

    private fun visibleTextInfo() {
        if (mainAdapter?.textList?.size != 0) {
            binding.bgTextList.visibility = android.view.View.INVISIBLE
        } else {
            binding.bgTextList.visibility = android.view.View.VISIBLE
        }
    }

    override fun invoke(textObject: TextData, position: Int) {
        presenter.deleteTextFromList(textObject)
    }
}