package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeongseunggyu.colocsseum.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        binding.loginBtn.setOnClickListener {
            //입력한 이메일 / 비밀번호가 뭔지 변수에 저장.
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            //서버에 실제 회원이 맞는지 확인 요청. (Request) ??

        }

    }

    override fun setValues() {
    }
}