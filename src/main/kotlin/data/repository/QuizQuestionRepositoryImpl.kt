package com.maulik.data.repository

import com.maulik.domain.model.QuizQuestion
import com.maulik.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl: QuizQuestionRepository {

    var quizQuestions = mutableListOf<QuizQuestion>()

    override fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return if (topicCode != null) {
            quizQuestions.filter { it.topicCode == topicCode }.take(limit ?: quizQuestions.size)
        } else {
            quizQuestions.take(limit ?: quizQuestions.size)
        }
    }

    override fun getQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override fun deleteQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }

    override fun insertQuizQuestion(quizQuestion: QuizQuestion) {
        quizQuestions.add(quizQuestion)
    }

    /*override fun updateQuizQuestion(id: String, quizQuestion: QuizQuestion) {
        TODO("Not yet implemented")
    }*/
}