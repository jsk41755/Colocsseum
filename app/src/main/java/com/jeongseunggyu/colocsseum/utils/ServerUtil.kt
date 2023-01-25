package com.jeongseunggyu.colocsseum.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {
    //어떤 내용?? => 서버 연결 전달
    companion object {

        interface JsonResponseHandler {
            fun onResponse(jsonObj : JSONObject)
        }

        //모든 기능의 기본이 되는 주소
        val BASE_URL = "http://54.180.52.26"

        //로그인 하는 기능
        fun postRequestLogin(email : String, pw : String, handler : JsonResponseHandler?){
//          서버에 입력받은 email, pw 전달 => 로그인 기능 POST / user 로 전달. => 요청(Request) 실행.
            // 라이브러리 (okHTTP) 활용해서 짜보자

            // http//54.180.52.26/user + POST + 파라미터들 첨부.

            //호스트 주소 + 기능 주소 결합
            val urlString = "${BASE_URL}/user"

            //포스트 방식 => 파라미터를 폼데이터(폼바디)에 담아두자.
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

            //어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

            //클라이언트로써의 동작 : Request 요청 실행. => OkHTTP라이브러리 지원.
            val client = OkHttpClient()

            //실제로 서버에 요청 날리기 => 갔다 와서는 뭘 한건지?
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    //서버의 연결 자체를 실패한 경우.
                    //서버 마비, 인터넷 단선. (로그인 실패X)
                    Log.d("응답 본문", "에ㅔ레렐ㄹ레")
                }

                override fun onResponse(call: Call, response: Response) {
                    //로그인 성공, 로그인 실패(연결 성공 -> 검사 실패)
                    //응답이 돌아온 경우.

                    //응답 본문을 String으로 저장
                    val bodyString = response.body!!.string()

                    //bodyString 변수에는 한글이 깨져있다. => JsonObject로 변환하면, 한글 정상 처리.
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답 본문", jsonObj.toString())

                    //handler 변수가 null이 아니라면, (실체가 있다면)
                    //그 내부에 적힌 내용 실행.

                    handler?.onResponse(jsonObj)
                }

            })
        }

    }
}