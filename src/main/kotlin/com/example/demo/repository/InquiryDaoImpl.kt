package com.example.demo.repository

import com.example.demo.entity.Inquiry
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
class InquiryDaoImpl(
        // TODO コンストラクターインジェクション
        private val jdbcTemplate: JdbcTemplate
) : InquiryDao {

    override fun insert(inquiry: Inquiry): Int {
        return jdbcTemplate.update(
                "INSERT INTO INQUIRY(name, email, contents, created) VALUES(?, ?, ?, ?)",
                inquiry.name, inquiry.email, inquiry.contents, inquiry.created)
    }

    override fun getAll(): List<Inquiry> {
        return jdbcTemplate.queryForList("SELECT id, name, email, contents, created FROM INQUIRY").map {
            Inquiry().apply {
                id = it["id"] as Int
                name = it["name"] as String
                email = it["email"] as String
                contents = it["contents"] as String
                created = (it["created"] as Timestamp).toLocalDateTime()
            }
        }
    }
}