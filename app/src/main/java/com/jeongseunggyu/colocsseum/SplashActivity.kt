package com.jeongseunggyu.colocsseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.jeongseunggyu.colocsseum.databinding.ActivitySignUpBinding
import com.jeongseunggyu.colocsseum.databinding.ActivitySplashBinding
import com.jeongseunggyu.colocsseum.utils.ContextUtil

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        //3초 후에 검사 -> 저장된 토큰이 있는지? 아니면, "" 인지?

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({
             if(ContextUtil.getToken(mContext) == ""){
                 // 저장된 토큰이 없다 => 로그인을 해야한다.
                 val myIntent = Intent(mContext, LoginActivity::class.java)
                 startActivity(myIntent)
             }
            else{
                //저장된 토큰이 있다 => 로그인이 되어있다. => 메인화면으로 이동.
                 val myIntent = Intent(mContext, MainActivity::class.java)
                 startActivity(myIntent)
             }

            finish()

        }, 3000)
    }

}