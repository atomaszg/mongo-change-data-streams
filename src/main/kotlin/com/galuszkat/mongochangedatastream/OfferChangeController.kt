package com.galuszkat.mongochangedatastream

import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct

@Controller
class OfferChangeController(
    private val service: OfferService
) {

    @PostConstruct
    fun listen() {
        println("Start listening ...")
        service.attachListener()
    }
}