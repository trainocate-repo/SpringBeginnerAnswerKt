package com.example.demo.entity


import java.time.LocalDateTime

data class Survey(
        var id: Int = 0,
        var age: Int = 0,
        var satisfaction: Int = 0,
        var comment: String? = null,
        var created: LocalDateTime = LocalDateTime.now())