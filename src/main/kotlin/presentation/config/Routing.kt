package com.maulik.presentation.config

import com.maulik.domain.QuizQuestion
import com.maulik.presentation.routes.quiz_question.getAllQuizQuestions
import com.maulik.presentation.routes.quiz_question.saveQuizQuestion
import com.maulik.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllQuizQuestions()
        saveQuizQuestion()
    }
}

var quizQuestions = mutableListOf<QuizQuestion>()