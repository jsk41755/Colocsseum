package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeongseunggyu.colocsseum.databinding.ActivityMainBinding
import com.jeongseunggyu.colocsseum.databinding.ActivitySignUpBinding
import com.jeongseunggyu.colocsseum.datas.Topic

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getTopicListFromServer()

    }

    fun getTopicListFromServer(){

        //서버에서 주제 목록을 받아오자 => /v2/main_info => ServerUtil에 기능 추가 필요.



    }


}