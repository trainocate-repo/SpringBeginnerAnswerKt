package com.example.demo.repository

import com.example.demo.entity.Survey
import org.springframework.stereotype.Repository

interface SurveyDao {
    fun getAll(): List<Survey>
    fun insert(survey: Survey): Int
    fun getCount(): Int
    fun getSum(): Int
}