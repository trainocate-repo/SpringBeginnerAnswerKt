package com.example.demo.repository

import com.example.demo.entity.Inquiry
import org.springframework.stereotype.Repository

interface InquiryDao {
    fun getAll(): List<Inquiry>
    fun insert(inquiry: Inquiry): Int
}