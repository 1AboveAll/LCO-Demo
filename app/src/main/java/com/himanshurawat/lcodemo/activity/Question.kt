package com.himanshurawat.lcodemo.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.himanshurawat.lcodemo.R
import com.himanshurawat.lcodemo.adapter.QuestionAdapter
import com.himanshurawat.lcodemo.retrofit.QuestionInterface
import com.himanshurawat.lcodemo.retrofit.QuestionResponse
import com.himanshurawat.lcodemo.util.Constant

import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.content_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Question : AppCompatActivity(), QuestionAdapter.OnItemClickListener {


    lateinit var adapter: QuestionAdapter
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setSupportActionBar(toolbar)

        pref = applicationContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)

        adapter = QuestionAdapter(arrayListOf(),this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        ad_image_view.setOnClickListener({
            val url = "https://courses.learncodeonline.in"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })


        val retrofit: Retrofit = Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

        val questionInterface: QuestionInterface =  retrofit.create(QuestionInterface::class.java)

        val questionCall = questionInterface.getQuestion()

        questionCall.enqueue(object: Callback<QuestionResponse>{
            override fun onResponse(call: Call<QuestionResponse>?, response: Response<QuestionResponse>?) {
                if(response != null){

                    val ques = response.body()

                    if(ques != null){
                        adapter.addList(ques.questions)
                        //SAVING API DATA TO SHARED PREFRENCES
                        pref.edit().putString(Constant.OFFLINE_DATA,Gson().toJson(response.body())).apply()
                    }

                }
            }

            override fun onFailure(call: Call<QuestionResponse>?, t: Throwable?) {

            }

        })

        if(adapter.list.isEmpty()){
            val questionResponse = Gson()
                    .fromJson(pref.getString(Constant.OFFLINE_DATA,""),QuestionResponse::class.java)
            adapter.addList(questionResponse.questions)
        }

    }



    override fun onQuestionSelected(id: Int) {
        val list = adapter.list
        val i= Intent(Question@this, QuestionView::class.java)
        i.putExtra(Constant.QUESTION,list[id].question)
        i.putExtra(Constant.ANSWER,list[id].Answer)
        startActivity(i)
    }

}
