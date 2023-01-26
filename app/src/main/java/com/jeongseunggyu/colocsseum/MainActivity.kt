package com.jeongseunggyu.colocsseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeongseunggyu.colocsseum.adapters.TopicAdapter
import com.jeongseunggyu.colocsseum.databinding.ActivityMainBinding
import com.jeongseunggyu.colocsseum.databinding.ActivitySignUpBinding
import com.jeongseunggyu.colocsseum.datas.Topic
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    lateinit var mTopicAdapter : TopicAdapter

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun setupEvents() {

        binding.topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickTopic = mTopicList[position]

            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic", clickTopic)
            startActivity(myIntent)


        }

    }

    override fun setValues() {
        getTopicListFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)
        binding.topicListView.adapter = mTopicAdapter

    }

    fun getTopicListFromServer(){

        //서버에서 주제 목록을 받아오자 => /v2/main_info => ServerUtil에 기능 추가 필요.
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                //서버에서 주제 목록을 받아온 상황.
                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

                //topicsArr 안에 있는 여러개의 주제들을 반복적으로 파싱 => for문 활용
                //배열에 10개 주제 : index -> 0~9 까지
                for(index in 0 until topicsArr.length()){

                    //index 위치에 맞는 주제들을 Topic 클래스 형태로 변환.
                    val topicObj = topicsArr.getJSONObject(index)
                    val topicData = Topic.getTopicDataFromJson(topicObj)

                    //mTopicList 추가.
                    mTopicList.add(topicData)

                }

                //어댑터가 먼저 세팅 되고 => 나중에 목록에 추가. => 새로고침 필요 (UI영향) 서버는 백그라운드에서 따로 돌음
                runOnUiThread{
                    mTopicAdapter.notifyDataSetChanged()
                }



            }

        })



    }


}