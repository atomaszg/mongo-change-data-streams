package com.galuszkat.mongochangedatastream

import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/offers")
class OfferController(
    private val service: OfferService
) {

    @PostMapping
    fun create(@RequestBody offer: Offer): Mono<Offer> = mono {
        service.save(offer)
    }
}