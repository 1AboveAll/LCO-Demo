package com.himanshurawat.lcodemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.himanshurawat.lcodemo.R
import com.himanshurawat.lcodemo.util.Ques

class QuestionAdapter(var list: List<Ques>,var listener: OnItemClickListener) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.question_recycler_view_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.questionTextView.text = list[position].question
        holder.bind(position,listener)
    }


    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var questionTextView = itemView.findViewById<TextView>(R.id.question_item_text_view)
        fun bind(itemPostion: Int, listener: OnItemClickListener){
            itemView.setOnClickListener({
                listener.onQuestionSelected(itemPostion)
            })
        }
    }

    interface OnItemClickListener{
        fun onQuestionSelected(id: Int)
    }


    fun addList(newList: List<Ques>){
        list = newList
        notifyDataSetChanged()
    }
}