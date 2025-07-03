package com.maulik.data.database

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase

object DatabaseFactory {
    fun create(): MongoDatabase{
        val connectionString  = ""
        val databaseName = ""
        val mongoClient = MongoClients.create(connectionString)
        return mongoClient.getDatabase(databaseName)
    }
}