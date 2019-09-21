package com.example.demo.app.survey

import com.example.demo.service.SurveyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/survey")
class SurveyController(
        private val surveyService: SurveyService
) {

    @GetMapping
    fun index(model: Model): String {

        val surveyList = surveyService.getAll()
        val average = surveyService.getSatisfactionAvg()

        model.addAttribute("surveyList", surveyList)
        model.addAttribute("average", average)
        model.addAttribute("title", "アンケート一覧")

        return "survey/index"
    }

    @GetMapping("/form")
    fun form(surveyForm: SurveyForm, model: Model): String {
        model.addAttribute("title", "アンケートフォーム")
        return "survey/form"
    }

    @PostMapping("/confirm")
    fun confirm(
            @Valid @ModelAttribute surveyForm: SurveyForm,
            result: BindingResult,
            model: Model): String {

        if (result.hasErrors()) return "survey/form"

        model.addAttribute("title", "確認ページ")

        // TODO @ModelAttributeを指定した場合、自動でModelにセットされている
//        model.addAttribute("surveyForm", surveyForm)

        return "survey/confirm"
    }

    @PostMapping("/complete")
    fun complete(
            @Valid @ModelAttribute surveyForm: SurveyForm,
            result: BindingResult,
            model: Model): String {

        if (result.hasErrors()) return "survey/form"

        surveyService.save(surveyForm)

        return "redirect:/survey/form?complete"
    }

}