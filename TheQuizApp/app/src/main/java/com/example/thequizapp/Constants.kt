package com.example.thequizapp

import kotlin.random.Random

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val countryList = mapOf(
            "Argentina" to R.drawable.ic_flag_of_argentina,
            "Australia" to R.drawable.ic_flag_of_australia,
            "Belgium" to R.drawable.ic_flag_of_belgium,
            "Brazil" to R.drawable.ic_flag_of_brazil,
            "Denmark" to R.drawable.ic_flag_of_denmark,
            "Fiji" to R.drawable.ic_flag_of_fiji,
            "Germany" to R.drawable.ic_flag_of_germany,
            "India" to R.drawable.ic_flag_of_india,
            "Kuwait" to R.drawable.ic_flag_of_kuwait,
            "New Zealand" to R.drawable.ic_flag_of_new_zealand)
        for(i in 1..10) {
            var keys = countryList.keys
            var random = (0 until (countryList.size - 1)).shuffled().last()
            var country1 : String = keys.elementAt(random)
            var country2 : String = keys.elementAt(random)
            var country3 : String = keys.elementAt(random)
            var country4 : String = keys.elementAt(random)
            while(country2 == country1) {
                random = (0 until (countryList.size - 1)).shuffled().last()
                country2 = keys.elementAt(random)
            }
            while(country3 == country1 || country3 == country2) {
                random = (0 until (countryList.size - 1)).shuffled().last()
                country3 = keys.elementAt(random)
            }
            while(country4 == country1 || country4 == country2 || country4 == country3) {
                random = (0 until (countryList.size - 1)).shuffled().last()
                country4 = keys.elementAt(random)
            }
            random = (1..4).random()
            var questionFlag = countryList[country1]
            if(random == 2) {
                questionFlag = countryList[country2]
            }
            else if(random == 3)    {
                questionFlag = countryList[country3]
            }
            else    {
                questionFlag = countryList[country4]
            }
            var tempQuestion = questionFlag?.let {
                Question(
                    i,
                    "What country does this flag belong to?",
                    questionFlag,
                    country1,
                    country2,
                    country3,
                    country4,
                    random
                )
            }
            if (tempQuestion != null) {
                questionsList.add(tempQuestion)
            }
        }
        return questionsList
    }
}