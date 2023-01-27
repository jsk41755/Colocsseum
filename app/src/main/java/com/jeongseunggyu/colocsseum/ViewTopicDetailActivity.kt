package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jeongseunggyu.colocsseum.adapters.ReplyAdapter
import com.jeongseunggyu.colocsseum.databinding.ActivitySplashBinding
import com.jeongseunggyu.colocsseum.databinding.ActivityViewTopicDetailBinding
import com.jeongseunggyu.colocsseum.datas.Reply
import com.jeongseunggyu.colocsseum.datas.Topic
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopic: Topic

    private lateinit var binding: ActivityViewTopicDetailBinding

    val mReplyList = ArrayList<Reply>()
    lateinit var mReplyAdapter : ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewTopicDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        binding.voteToFirstSideBtn.setOnClickListener {
            //API 확인 => 토큰(ContextUtil) + 어떤 진영 선택? (해당 진영의 id값)

            ServerUtil.postRequestVote(mContext,mTopic.sides[0].id, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    //서버 응답 대응. => 서버에서 최신 투표 현황을 받아서, 다시 UI에 반영.
                    // 만들어둔 함수 재활용

                    getTopicDetailFromServer()

                }
            })
        }

        binding.voteToSecondSideBtn.setOnClickListener {
            //API 확인 => 토큰(ContextUtil) + 어떤 진영 선택? (해당 진영의 id값)

            ServerUtil.postRequestVote(mContext,mTopic.sides[1].id, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    //서버 응답 대응. => 서버에서 최신 투표 현황을 받아서, 다시 UI에 반영.
                    // 만들어둔 함수 재활용

                    getTopicDetailFromServer()

                }
            })
        }
    }

    override fun setValues() {

        mTopic = intent.getSerializableExtra("topic") as Topic

        binding.topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imageURL).into(binding.topicImg)

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item, mReplyList)
        binding.replyListView.adapter = mReplyAdapter

        //현재 투표 현황을 다시 서버에서 받아오자.
        getTopicDetailFromServer()

    }

    fun getTopicDetailFromServer(){

        ServerUtil.getRequestTopicDetail(mContext, mTopic.id, object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

                val topic = Topic.getTopicDataFromJson(topicObj)

                mTopic = topic

                //topicObj 내부의 replies JSONArray 파싱 => 의견 목록에 담아주자.

                val replyArr = topicObj.getJSONArray("replies")

                for (i in 0 until replyArr.length()){

                    val replyObj = replyArr.getJSONObject(i)
                    val reply = Reply.getReplyFromJson(replyObj)
                    mReplyList.add(reply)

                }

                //최신 득표 현황까지 받아서 mTopic에 저장됨.
                //UI에 득표 현황 반영


                //서버에 갔다와서 수정해주니 바꿔야 함.
                runOnUiThread {
                    binding.firstSideTxt.text = mTopic.sides[0].title
                    binding.firstSideVoteCountTxt.text = "${mTopic.sides[0].voteCount}표"

                    binding.secondSideTxt.text = mTopic.sides[1].title
                    binding.secondSideVoteCountTxt.text = "${mTopic.sides[1].voteCount}표"

                    //댓글 목록 새로고침
                    mReplyAdapter.notifyDataSetChanged()

                }


            }

        })

    }


}