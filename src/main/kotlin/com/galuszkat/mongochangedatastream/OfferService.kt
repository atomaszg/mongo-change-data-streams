package com.galuszkat.mongochangedatastream

import org.springframework.stereotype.Service

@Service
class OfferService(
    private val repository: OfferRepository
) {

    suspend fun save(offer: Offer): Offer {
        val document = offer.toDocument()
        val domain = repository.save(document).toDomain()

        return domain
    }
}