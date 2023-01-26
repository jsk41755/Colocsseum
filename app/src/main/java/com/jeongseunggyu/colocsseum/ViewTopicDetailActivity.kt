package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jeongseunggyu.colocsseum.databinding.ActivitySplashBinding
import com.jeongseunggyu.colocsseum.databinding.ActivityViewTopicDetailBinding
import com.jeongseunggyu.colocsseum.datas.Topic
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopic: Topic

    private lateinit var binding: ActivityViewTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewTopicDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        TODO("Not yet implemented")
    }

    override fun setValues() {

        mTopic = intent.getSerializableExtra("topic") as Topic

        binding.topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imageURL).into(binding.topicImg)

        //현재 투표 현황을 다시 서버에서 받아오자.
        getTopicDetailFromServer()

    }

    fun getTopicDetailFromServer(){

        ServerUtil.getRequestTopicDetail(mContext, mTopic.id, object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

            }

        })

    }


}