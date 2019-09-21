package com.example.demo.app.inquiry

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.io.Serializable

class InquiryForm : Serializable {

    var id: Int = 0

    @NotNull(message = "タイトルを入力してください")
    @Size(min = 1, max = 20, message = "タイトルは20文字以内で入力してください")
    var name: String? = null

    @NotNull(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が間違っています")
    var email: String? = null

    @NotNull(message = "お問い合わせ内容を入力してください")
    var contents: String? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}