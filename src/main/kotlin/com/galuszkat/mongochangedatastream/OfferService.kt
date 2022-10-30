package com.galuszkat.mongochangedatastream

import com.mongodb.client.model.changestream.OperationType
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.bson.BsonTimestamp
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.changeStream
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class OfferService(
    private val repository: OfferRepository,
    private val mongoTemplate: ReactiveMongoTemplate,
    private val ops: ReactiveMongoOperations
) {

    suspend fun save(offer: Offer): Offer {
        val document = offer.toDocument()
        return repository.save(document)
            .toDomain()
    }

    suspend fun saveTransactional(offer: Offer): Offer {

        val offer2 = offer.copy(id= offer.id+1)

        return ops.inTransaction()
            .execute {
                it.insert(offer.toDocument())
                    .then(it.insert(offer2.toDocument()))
            }
            .awaitFirstOrNull()!!.toDomain()
    }

    fun attachListener() {
        mongoTemplate.changeStream<OfferDocument>()
            .watchCollection("offers")
            .listen()
            .doOnNext {
                val operationType: OperationType? = it.operationType
                val timestamp: Instant? = it.timestamp
                val timestampBson: BsonTimestamp? = it.bsonTimestamp
                val offerDocument: OfferDocument? = it.body

                println("operationType = ${operationType}")
                println("offerDocument = ${offerDocument}")
                println("timestamp = ${timestamp}")
                println("timestampBson = ${timestampBson}")
            }
            .doFinally {
                println("Finished listening = ${it}")
            }
            .subscribe()
    }
}