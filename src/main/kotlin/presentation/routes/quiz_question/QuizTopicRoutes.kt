package com.maulik.presentation.routes.quiz_question

import com.maulik.domain.QuizTopic
import com.maulik.presentation.config.quizTopics
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.saveQuizTopic(){
    post(path = "/quiz/topics"){
        val quizTopic =  call.receive<QuizTopic>()
        quizTopics.add(quizTopic)
        call.respondText("Topic save successfully!")
    }
}

fun Route.getAllQuizTopics(){
    get (path = "/quiz/topics"){
        call.respond(quizTopics)
    }
}