package com.example.demo.config

import com.example.demo.service.EmptyListException
import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder


/**
 * 全てのControllerで共通処理を定義
 */
@ControllerAdvice
class WebMvcControllerAdvice {

    @InitBinder//重複してる？RegistrationControllerと
    fun initBinder(dataBinder: WebDataBinder) {
        // Stringの空文字をNULLに
        dataBinder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))
    }

    //アプリケーション全体で共通的に行う例外処理
    @ExceptionHandler(BadSqlGrammarException::class)
    fun handleException(e: BadSqlGrammarException) = "err/SQL"

    @ExceptionHandler(EmptyListException::class)
    fun handleException(e: EmptyListException) = "err/EmptyList"

}