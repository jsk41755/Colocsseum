package com.jeongseunggyu.colocsseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jeongseunggyu.colocsseum.databinding.ActivityEditReplyBinding
import com.jeongseunggyu.colocsseum.databinding.ActivityViewTopicDetailBinding
import com.jeongseunggyu.colocsseum.datas.Side
import com.jeongseunggyu.colocsseum.utils.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

    lateinit var mSelectedSide : Side

    private lateinit var binding: ActivityEditReplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditReplyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.okBtn.setOnClickListener {

            val inputContent = binding.contentEdt.text.toString()

            ServerUtil.postRequestReply(mContext, mSelectedSide.topicId, inputContent, object  : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if(code == 200){
                        Toast.makeText(mContext, "의견 등록에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        finish()

                    }
                    else{
                        val message = jsonObj.getString("message")
                        runOnUiThread {

                            Toast.makeText(mContext,message, Toast.LENGTH_SHORT).show()

                        }
                    }

                }

            })

        }

    }

    override fun setValues() {

        mSelectedSide = intent.getSerializableExtra("mySide") as Side

        binding.mySelectedSideTxt.text = mSelectedSide.title
    }


}