package com.jeongseunggyu.colocsseum.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var selectedSide : Side

    companion object{

        fun getReplyFromJson(jsonObj: JSONObject) : Reply {
            val resultReply = Reply()

            resultReply.id = jsonObj.getInt("id")
            resultReply.content = jsonObj.getString("content")

            resultReply.selectedSide = Side.getSideFromJson(jsonObj.getJSONObject("selected_side")) //Side형태로 타입 변환


            return resultReply
        }

    }

}