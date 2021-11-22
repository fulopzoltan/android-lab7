package com.example.android_lab3quiz

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("response_code")
    val responseCode : Int,
    val results : List<Results>
)

data class Results(
    val category : String,
    val type : String,
    val difficulty : String,
    val question : String,

    @SerializedName("correct_answer")
    val correctAnswer : String,

    @SerializedName("incorrect_answers")
    val incorrectAnswers : List<String>

)