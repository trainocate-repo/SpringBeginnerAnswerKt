package com.example.demo.app.survey

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import java.io.Serializable

class SurveyForm : Serializable {

    var id: Int = 0

    @Min(1, message = "年齢を入力してください")
    @Max(150)
    var age: Int = 0

    @Range(min = 1, max = 5)
    var satisfaction: Int = 0

    var comment: String? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}