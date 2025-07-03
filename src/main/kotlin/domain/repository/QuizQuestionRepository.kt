package com.maulik.domain.repository

import com.maulik.domain.model.QuizQuestion

interface QuizQuestionRepository {
    suspend fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    suspend fun getQuestionById(id: String): QuizQuestion?
    suspend fun deleteQuestionById(id: String): Boolean
    suspend fun insertQuizQuestion(quizQuestion: QuizQuestion)
    /*fun updateQuizQuestion(id: String, quizQuestion: QuizQuestion)*/
}