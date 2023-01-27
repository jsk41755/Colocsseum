package com.jeongseunggyu.colocsseum.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jeongseunggyu.colocsseum.R
import com.jeongseunggyu.colocsseum.datas.Reply
import com.jeongseunggyu.colocsseum.datas.Topic
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class ReplyAdapter(
    val mContext : Context,
    resId : Int,
    val mList : List<Reply>) : ArrayAdapter<Reply>(mContext, resId, mList){

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null){
            tempRow = mInflater.inflate(R.layout.reply_list_item, null)//루트는 부가 정보
        }

        val row = tempRow!!

        val data = mList[position]

        val selectSideTxt = row.findViewById<TextView>(R.id.selectSideTxt)
        val userNicknameTxt = row.findViewById<TextView>(R.id.userNicknameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        val likeCountBtn = row.findViewById<TextView>(R.id.likeCountBtn)
        val dislikeCountBtn = row.findViewById<TextView>(R.id.dislikeCountBtn)

        contentTxt.text = data.content

        selectSideTxt.text = "(${data.selectedSide.title})"

        userNicknameTxt.text = data.writerNickname

        likeCountBtn.text = "좋아요 ${data.likeCount}개"
        dislikeCountBtn.text = "싫어요 ${data.dislikeCount}개"

        if(data.myLike){
            //좋아요만. 글씨 빨간색 + 배경 빨간 테두리 싫어요 -> 회색
            likeCountBtn.setBackgroundResource(R.drawable.red_border_box)
            likeCountBtn.setTextColor(Color.parseColor("#ff0000"))

            dislikeCountBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeCountBtn.setTextColor(Color.parseColor("#A0A0A0"))

        }
        else if(data.myDislke){
            //싫어요만 글씨 파란색 + 배경도 파란 테두리 좋아요 -> 회색
            likeCountBtn.setBackgroundResource(R.drawable.gray_border_box)
            likeCountBtn.setTextColor(Color.parseColor("#A0A0A0"))

            dislikeCountBtn.setBackgroundResource(R.drawable.blue_border_box)
            dislikeCountBtn.setTextColor(Color.parseColor("#0000FF"))
        }
        else{
            //좋아요 / 싫어요 찍지 않은 상태. -> 둘다 회색
            likeCountBtn.setBackgroundResource(R.drawable.gray_border_box)
            likeCountBtn.setTextColor(Color.parseColor("#A0A0A0"))

            dislikeCountBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeCountBtn.setTextColor(Color.parseColor("#D0D0D0"))

        }

        likeCountBtn.setOnClickListener{
//          좋아요 API 호출
            ServerUtil.postRequestLikeOrDislike(mContext, data.id, true, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    TODO("Not yet implemented")
                }

            })
        }

        dislikeCountBtn.setOnClickListener {
            //싫어요 API 호출
            ServerUtil.postRequestLikeOrDislike(mContext, data.id, false, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    TODO("Not yet implemented")
                }

            })

        }

        return row
    }


}