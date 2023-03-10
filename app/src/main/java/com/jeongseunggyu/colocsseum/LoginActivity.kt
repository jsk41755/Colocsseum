package com.jeongseunggyu.colocsseum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.jeongseunggyu.colocsseum.databinding.ActivityLoginBinding
import com.jeongseunggyu.colocsseum.utils.ContextUtil
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        binding.signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }


        binding.loginBtn.setOnClickListener {
            //입력한 이메일 / 비밀번호가 뭔지 변수에 저장.
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            //서버에 실제 회원이 맞는지 확인 요청. (Request)
            ServerUtil.postRequestLogin(inputEmail, inputPw, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    //jsonObj : 서버에서 내려준 본문을 JSON 형태로 가공해준 결과물.
                    // => 이 내부를 파싱(분석)해서, 상황에 따른 대응.
                    // => ex. 로그인 실패시, 그 이유를 토스트로 띄운다던지.

                    val code = jsonObj.getInt("code")
                    if(code == 200){
                        //로그인 성공

                        //서버가 주는 토큰을 추출해서 저장
                        val dataObj = jsonObj.getJSONObject("data")
                        val token = dataObj.getString("token")

                        ContextUtil.setToken(mContext, token)

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()


                    }
                    else{
                        //로그인 실패
                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }

                }

            })
        }

    }

    override fun setValues() {


    }
}