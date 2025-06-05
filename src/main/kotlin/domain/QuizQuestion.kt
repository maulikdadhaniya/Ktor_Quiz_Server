package com.maulik.domain

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestion(
    val id: String? = null,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val explanation: Int,
    val topicCode: Int
)
