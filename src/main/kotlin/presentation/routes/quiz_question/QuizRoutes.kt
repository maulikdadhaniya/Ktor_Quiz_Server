package com.maulik.presentation.routes.quiz_question

import com.maulik.data.repository.QuizQuestionRepositoryImpl
import com.maulik.domain.model.QuizQuestion
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    quizQuestionRepositoryImpl: QuizQuestionRepositoryImpl
) {
    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val filterQuestions = quizQuestionRepositoryImpl.getAllQuestions(topicCode, limit)
        if (filterQuestions.isNotEmpty()) {
            call.respond(
                message = filterQuestions, status = HttpStatusCode.OK
            )
        } else {
            call.respond(message = "No Quiz Found!", status = HttpStatusCode.NotFound)
        }
        call.respond(filterQuestions)
    }
}

fun Route.saveQuizQuestion(
    repository: QuizQuestionRepositoryImpl
) {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        repository.insertQuizQuestion(question)
        call.respond(
            message = "Question save successfully!", status = HttpStatusCode.Created
        )
    }
}

fun Route.deleteQuizQuestion(
    repository: QuizQuestionRepositoryImpl
) {
    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                status = HttpStatusCode.BadRequest, message = "questionId needed!"
            )
            return@delete
        }
        val isDeleted = repository.deleteQuestionById(id)

        if (isDeleted) {
            call.respond(
                message = "Quiz Delete Successfully", status = HttpStatusCode.NoContent
            )
        } else {
            call.respond(
                message = "Quiz not deleted!", status = HttpStatusCode.NotFound
            )
        }

    }
}

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepositoryImpl
) {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                status = HttpStatusCode.BadRequest, message = "QuestionId needed"
            )
            return@get
        }
        val quizQuestion: QuizQuestion? = repository.getQuestionById(id)
        if (quizQuestion != null) {
            call.respond(
                message = quizQuestion, status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "No Quiz Questions", status = HttpStatusCode.NotFound
            )
        }
    }
}

/*fun Route.updateQuizQuestion() {
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
}*/
