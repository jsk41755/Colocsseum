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
import com.jeongseunggyu.colocsseum.datas.Reply
import com.jeongseunggyu.colocsseum.datas.Topic

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

        contentTxt.text = data.content


        return row
    }


}