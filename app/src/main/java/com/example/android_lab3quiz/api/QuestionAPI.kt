package com.example.android_lab3quiz.api

import com.example.android_lab3quiz.BaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionAPI {

    @GET("api.php")
    suspend fun getQuestions(@Query("amount") amount :Int ) : Response<BaseModel>
}