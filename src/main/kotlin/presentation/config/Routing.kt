package com.maulik.presentation.config

import com.maulik.data.repository.QuizQuestionRepositoryImpl
import com.maulik.domain.model.QuizQuestion
import com.maulik.domain.model.QuizTopic
import com.maulik.presentation.routes.quiz_question.*
import com.maulik.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val quizQuestionRepositoryImpl = QuizQuestionRepositoryImpl()
    routing {
        root()
        //Quiz Topics
        saveQuizTopic()
        getAllQuizTopics()

        //Quiz Questions
        getAllQuizQuestions(quizQuestionRepositoryImpl)
        saveQuizQuestion(quizQuestionRepositoryImpl)
        deleteQuizQuestion(quizQuestionRepositoryImpl)
        getQuizQuestionById(quizQuestionRepositoryImpl)
        /*updateQuizQuestion()*/
    }
}

var quizTopics = mutableListOf<QuizTopic>()