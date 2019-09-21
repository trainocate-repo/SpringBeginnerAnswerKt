package com.example.demo.entity

import java.time.LocalDateTime

data class Inquiry(
        var id: Int = 0,
        var name: String = "",
        var email: String = "",
        var contents: String = "",
        var created: LocalDateTime = LocalDateTime.now())
