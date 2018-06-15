package com.himanshurawat.lcodemo.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface QuestionInterface {


    @GET("android/datastructure.json")
    fun getQuestion(): Call<QuestionResponse>

}