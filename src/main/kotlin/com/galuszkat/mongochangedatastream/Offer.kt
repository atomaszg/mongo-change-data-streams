package com.galuszkat.mongochangedatastream

import java.time.Instant

data class Offer(
    val id: Long,
    val status: String,
    val updatedAt: Instant?
) {
     fun  toDocument(): OfferDocument =
         OfferDocument(
             id = this.id,
             status = this.status,
             updatedAt = this.updatedAt ?: Instant.now()
         )
}