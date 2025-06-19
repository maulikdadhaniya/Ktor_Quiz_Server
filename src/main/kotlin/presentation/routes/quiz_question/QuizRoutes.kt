package com.maulik.presentation.routes.quiz_question

import com.maulik.domain.QuizQuestion
import com.maulik.presentation.config.quizQuestions
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.getAllQuizQuestions() {
    get(path = "/quiz/questions") {
        call.respond(quizQuestions)
    }
}

fun Route.saveQuizQuestion(){
    post(path = "/quiz/questions"){
       val question =  call.receive<QuizQuestion>()
        quizQuestions.add(question)
        call.respondText("Question save successfully!")
    }
}