package com.example.demo.service

import com.example.demo.app.survey.SurveyForm
import com.example.demo.entity.Survey
import com.example.demo.repository.SurveyDao
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SurveyServiceImpl(
        private val dao: SurveyDao
) : SurveyService {

    override fun save(surveyForm: SurveyForm): Int {
        val survey = Survey()
        survey.age = surveyForm.age
        survey.satisfaction = surveyForm.satisfaction
        survey.comment = surveyForm.comment
        survey.created = LocalDateTime.now()
        return dao.insert(survey)
    }

    override fun getAll(): List<Survey> {
        val list = dao.getAll()
        if (list.isEmpty()) throw EmptyListException("SQL error")
        return list
    }

    override fun getSatisfactionAvg(): Double {
        val count = dao.getCount().toDouble()

        if(count == 0.0) return 0.0

        val sum = dao.getSum().toDouble()
        return sum / count
    }
}