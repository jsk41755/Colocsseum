package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jeongseunggyu.colocsseum.databinding.ActivityMainBinding
import com.jeongseunggyu.colocsseum.databinding.ActivitySignUpBinding
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.signUpBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()
            val inputNickname = binding.nicknameEdt.text.toString()

            ServerUtil.putRequestSignUp(inputEmail, inputPw, inputNickname, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if (code == 200){

                        //가입한 사람의 닉네임 추출 => 토스트로 ~~님 환영합니다! + 회원가입 화면 종료 => 로그인화면으로 복귀.

                        //jsonObj { } => data { } => user { } => 내부에서 nick_name String 추출.

                        val dataObj = jsonObj.getJSONObject("data")

                        val userObj = dataObj.getJSONObject("user")

                        val nickname = userObj.getJSONObject("nick_name")

                        runOnUiThread{
                            Toast.makeText(mContext, "${nickname}님, 환영합니다!", Toast.LENGTH_SHORT).show()
                            finish()
                        }




                    }
                    else{
                        //실패 사유를 => 서버가 주는 message에 담긴 문구로 출력
                        val message = jsonObj.getString("message")

                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })
        }

    }

    override fun setValues() {
        TODO("Not yet implemented")
    }


}