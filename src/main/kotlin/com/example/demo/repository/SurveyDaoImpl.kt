package com.example.demo.repository

import com.example.demo.entity.Survey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import java.sql.Timestamp
import java.util.ArrayList

@Repository
class SurveyDaoImpl(
        private val jdbcTemplate: JdbcTemplate
) : SurveyDao {

    override fun insert(survey: Survey): Int {
        return jdbcTemplate.update("INSERT INTO survey(age, satisfaction, comment, created) VALUES(?, ?, ?, ?)",
                survey.age, survey.satisfaction, survey.comment, survey.created)
    }

    override fun getAll(): List<Survey> {
        return jdbcTemplate.queryForList("SELECT id, age, satisfaction, comment, created FROM survey").map {
            Survey().apply {
                id = it["id"] as Int
                age = it["age"] as Int
                satisfaction = it["satisfaction"] as Int
                comment = it["comment"] as String
                created = (it["created"] as Timestamp).toLocalDateTime()
            }
        }
    }

    override fun getCount(): Int {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM survey", Int::class.java) ?: 0
    }

    override fun getSum(): Int {
        return jdbcTemplate.queryForObject("SELECT SUM(satisfaction) FROM survey", Int::class.java) ?: 0
    }


}