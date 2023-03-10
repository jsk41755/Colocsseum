package com.jeongseunggyu.colocsseum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jeongseunggyu.colocsseum.R
import com.jeongseunggyu.colocsseum.datas.Topic

class TopicAdapter(
    val mContext : Context,
    resId : Int,
    val mList : List<Topic>) : ArrayAdapter<Topic>(mContext, resId, mList){

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null){
            tempRow = mInflater.inflate(R.layout.topic_list_item, null)//루트는 부가정보
        }

        val row = tempRow!!

        val data = mList[position]

        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val topicTitleTxt = row.findViewById<TextView>(R.id.topicTitleTxt)

        topicTitleTxt.text = data.title
        //서버가 주는 이미지 URL => 그림 이미지뷰에 표시
        Glide.with(mContext).load(data.imageURL).into(topicImg)

        return row
    }


}