package com.jeongseunggyu.colocsseum.datas

import org.json.JSONObject

class Topic : java.io.Serializable{

    var id = 0 //나중에 Int가 들어올 것이라는 것을 명시
    var title = "" //나중에 String이 들어올 것이라는 명시
    var imageURL = ""

    val sides = ArrayList<Side>()

    companion object{

        //적당한 모양이 되어 있는 JsonObject 하나를 넣어 주면 => Topic 형태로 변환해주는 함수 작성
        fun getTopicDataFromJson(jsonObj : JSONObject) : Topic {

            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imageURL = jsonObj.getString("img_url")

            val sidesArr= jsonObj.getJSONArray("sides")

            for (i in 0 until sidesArr.length()){

                //토픽마다 하위정보로 달린 => 선택진영을 파싱.
                val sidObj = sidesArr.getJSONObject(i)
                val side = Side.getSideFromJson(sidObj)

                resultTopic.sides.add(side)

            }

            return resultTopic

        }

    }

}