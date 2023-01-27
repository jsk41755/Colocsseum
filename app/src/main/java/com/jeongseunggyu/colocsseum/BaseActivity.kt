package com.jeongseunggyu.colocsseum

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity(){

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar(){

        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_cumstom_action_bar)

        val myToolbar = defaultActionBar.customView.parent as Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)

    }
}