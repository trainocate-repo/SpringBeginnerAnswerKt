package com.example.demo.service

import com.example.demo.app.inquiry.InquiryForm
import com.example.demo.entity.Inquiry
import com.example.demo.repository.InquiryDao
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InquiryServiceImpl(
        private val dao: InquiryDao
) : InquiryService {

    override fun getAll(): List<Inquiry> {
        return dao.getAll().apply {
            if(this.isEmpty()) throw EmptyListException("SQL error")
        }
    }

    override fun save(inquiryForm: InquiryForm): Int {
        return Inquiry(
                name = inquiryForm.name!!,
                email = inquiryForm.email!!,
                contents = inquiryForm.contents!!,
                created = LocalDateTime.now()
        ).run { dao.insert(this) }
    }
}