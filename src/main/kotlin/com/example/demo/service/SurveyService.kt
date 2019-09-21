package com.example.demo.service

import com.example.demo.app.survey.SurveyForm
import com.example.demo.entity.Survey

interface SurveyService {

    fun getAll(): List<Survey>

    fun getSatisfactionAvg(): Double

    fun save(surveyForm: SurveyForm): Int

}