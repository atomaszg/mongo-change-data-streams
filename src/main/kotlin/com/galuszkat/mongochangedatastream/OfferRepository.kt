package com.galuszkat.mongochangedatastream

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository: CoroutineCrudRepository<OfferDocument, Long> {
}