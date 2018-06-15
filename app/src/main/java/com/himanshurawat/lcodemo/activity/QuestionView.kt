package com.himanshurawat.lcodemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.himanshurawat.lcodemo.R
import com.himanshurawat.lcodemo.util.Constant
import kotlinx.android.synthetic.main.activity_question_view.*
import android.content.Intent
import android.net.Uri


class QuestionView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_view)

        val ab = supportActionBar

        if(ab != null){
            ab.setHomeButtonEnabled(true)
        }

        if(intent.extras != null){
            question_text_view.text = intent.getStringExtra(Constant.QUESTION)
            answer_text_view.text = intent.getStringExtra(Constant.ANSWER)
        }

        ad_image_view.setOnClickListener({
            val url = "https://courses.learncodeonline.in"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })
    }

}
