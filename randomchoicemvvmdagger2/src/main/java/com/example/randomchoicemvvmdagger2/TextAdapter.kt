package com.example.randomchoicemvvmdagger2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomchoicemvvmdagger2.model.TextData

typealias OnTextClickListener = (textObject: TextData, position: Int) -> Unit

class TextAdapter (
    private val textList: MutableList<TextData>,
    private val onTextClick: OnTextClickListener?
    ) : RecyclerView.Adapter<TextAdapter.TextHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return TextHolder(view)
    }

    override fun onBindViewHolder(holder: TextHolder, position: Int) {
        val textObject = textList[position]
        holder.bind(textObject, onTextClick)
    }

    override fun getItemCount() = textList.size

    class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var textView: TextView = itemView.findViewById(R.id.text_item_in_list)
        private var buttonDeletedText: ImageView = itemView.findViewById(R.id.button_deleted_this_text)

        fun bind (textObject: TextData, onTextClick: OnTextClickListener?) {
            textView.text = textObject.text

            buttonDeletedText.setOnClickListener {
                onTextClick?.invoke(textObject, adapterPosition)
            }
        }
    }
}