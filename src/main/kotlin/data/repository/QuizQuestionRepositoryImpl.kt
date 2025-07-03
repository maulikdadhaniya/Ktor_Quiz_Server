package com.maulik.data.repository

import com.maulik.domain.model.QuizQuestion
import com.maulik.domain.repository.QuizQuestionRepository
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
): QuizQuestionRepository {

    private val questionCollection = run {

        val pojoCodecRegistry = CodecRegistries.fromRegistries(
            mongoDatabase.codecRegistry,
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )

        // Get collection with proper codec
        mongoDatabase.getCollection("quiz_questions", QuizQuestion::class.java)
            .withCodecRegistry(pojoCodecRegistry)
    }
    var quizQuestions = mutableListOf<QuizQuestion>()

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return if (topicCode != null) {
            quizQuestions.filter { it.topicCode == topicCode }.take(limit ?: quizQuestions.size)
        } else {
            quizQuestions.take(limit ?: quizQuestions.size)
        }
    }

    override suspend fun getQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override suspend fun deleteQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }

    override suspend fun insertQuizQuestion(quizQuestion: QuizQuestion) {
        quizQuestions.add(quizQuestion)
        questionCollection.insertOne(quizQuestion)
    }

    /*override fun updateQuizQuestion(id: String, quizQuestion: QuizQuestion) {
        TODO("Not yet implemented")
    }*/
}