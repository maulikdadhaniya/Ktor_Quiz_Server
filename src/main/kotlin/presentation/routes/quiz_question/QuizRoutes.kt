package com.maulik.presentation.routes.quiz_question

import com.maulik.domain.QuizQuestion
import com.maulik.presentation.config.quizQuestions
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put

fun Route.getAllQuizQuestions() {
    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val filterQuestions = quizQuestions
            .filter { it.topicCode == topicCode }
            .take(limit ?: 1)
        call.respond(filterQuestions)
    }
}

fun Route.saveQuizQuestion() {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        quizQuestions.add(question)
        call.respondText("Question save successfully!")
    }
}

fun Route.deleteQuizQuestion() {
    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        quizQuestions.removeIf { it.id == id }
        call.respondText("Quiz Delete Successfully")
    }
}

fun Route.getQuizQuestionById() {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        val quizQuestion: QuizQuestion? = quizQuestions.find { it.id == id }
        if (quizQuestion != null)
            call.respond(quizQuestion)
    }
}

fun Route.updateQuizQuestion() {
    put("/quiz/questions/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid or missing question ID.")
            return@put
        }

        val updatedQuestion = call.receive<QuizQuestion>()

        val index = quizQuestions.indexOfFirst { it.id == id }
        if (index == -1) {
            call.respond(HttpStatusCode.NotFound, "Quiz question not found.")
            return@put
        }

        quizQuestions[index] = updatedQuestion
        call.respond(HttpStatusCode.OK, "Quiz question updated successfully.")
    }
}
