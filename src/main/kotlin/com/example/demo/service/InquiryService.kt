package com.example.demo.service

import com.example.demo.app.inquiry.InquiryForm
import com.example.demo.entity.Inquiry

interface InquiryService {

    fun getAll(): List<Inquiry>

    fun save(inquiryForm: InquiryForm): Int

}