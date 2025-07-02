package com.maulik.presentation.config

import com.maulik.domain.QuizQuestion
import com.maulik.domain.QuizTopic
import com.maulik.presentation.routes.quiz_question.deleteQuizQuestion
import com.maulik.presentation.routes.quiz_question.getAllQuizQuestions
import com.maulik.presentation.routes.quiz_question.getAllQuizTopics
import com.maulik.presentation.routes.quiz_question.getQuizQuestionById
import com.maulik.presentation.routes.quiz_question.saveQuizQuestion
import com.maulik.presentation.routes.quiz_question.saveQuizTopic
import com.maulik.presentation.routes.quiz_question.updateQuizQuestion
import com.maulik.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        //Quiz Topics
        saveQuizTopic()
        getAllQuizTopics()

        //Quiz Questions
        getAllQuizQuestions()
        saveQuizQuestion()
        deleteQuizQuestion()
        getQuizQuestionById()
        updateQuizQuestion()
    }
}

var quizQuestions = mutableListOf<QuizQuestion>()
var quizTopics = mutableListOf<QuizTopic>()