package com.example.thequizapp

object Constants {
    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val question1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Peru",
            "Israel",
            "Indonesia",
            1
        )
        questionsList.add(question1)
        return questionsList
    }
}