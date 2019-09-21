package com.example.demo.app.inquiry

import com.example.demo.entity.Inquiry
import com.example.demo.service.InquiryService
import com.example.demo.service.InquiryServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@Controller
@RequestMapping("/inquiry")
class InquiryController(private val inquiryService: InquiryService) {

    @GetMapping
    fun index(model: Model): String {

        val inquiryList = inquiryService.getAll()

        model.addAttribute("inquiryList", inquiryList)
        model.addAttribute("title", "お問い合わせ一覧")

        return "inquiry/index"
    }

    @GetMapping("/form")
    fun form(inquiryForm: InquiryForm, model: Model): String {
        model.addAttribute("title", "お問い合わせフォーム")
        return "inquiry/form"
    }


    @PostMapping("/test")
    fun test(
            @RequestParam name: String,
            @RequestParam email: String,
            @RequestParam contents: String,
            model: Model): String {
        model.addAttribute("title", "テスト")
        model.addAttribute("name", name)
        model.addAttribute("email", email)
        model.addAttribute("contents", contents)
        return "inquiry/test"
    }

    @PostMapping("/confirm")
    fun confirm(
//            @Valid @ModelAttribute inquiryForm: InquiryForm,
            // TODO @Validより@Validatedがbetterです
            @Validated @ModelAttribute inquiryForm: InquiryForm,
            result: BindingResult,
            model: Model): String {

        if (result.hasErrors()) return "inquiry/form"

        model.addAttribute("title", "確認ページ")
//        model.addAttribute("inquiryForm", inquiryForm)
        return "inquiry/confirm"
    }

    @PostMapping("/complete")
    fun complete(
            @Valid @ModelAttribute inquiryForm: InquiryForm,
            result: BindingResult,
            model: Model): String {

        if (result.hasErrors()) return "inquiry/form"

        inquiryService.save(inquiryForm)
        return "redirect:/inquiry/form?complete"
    }
}