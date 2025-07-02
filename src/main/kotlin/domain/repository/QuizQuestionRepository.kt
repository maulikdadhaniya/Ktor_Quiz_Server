package com.maulik.domain.repository

import com.maulik.domain.model.QuizQuestion

interface QuizQuestionRepository {
    fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    fun getQuestionById(id: String): QuizQuestion?
    fun deleteQuestionById(id: String): Boolean
    fun insertQuizQuestion(quizQuestion: QuizQuestion)
    /*fun updateQuizQuestion(id: String, quizQuestion: QuizQuestion)*/
}