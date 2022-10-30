package com.galuszkat.mongochangedatastream

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@TypeAlias("offer")
@Document("offers")
data class OfferDocument(
    @Id
    private val id: Long,
    private val status: String,
    private val updatedAt: Instant
) {
    fun toDomain(): Offer =
        Offer(
            id = this.id,
            status = this.status,
            updatedAt = this.updatedAt
        )
}