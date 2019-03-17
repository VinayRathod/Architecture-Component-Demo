package com.vinay.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.vinay.R
import com.vinay.architecturecomponent.db.entity.Word
import com.vinay.architecturecomponent.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class SaveActivity : AppCompatActivity() {
     var wordViewModel: WordViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
    }

    fun onClick(view: View) {
        if (!TextUtils.isEmpty(edt_name.getText().toString())) {
            val word = Word()
            word.word = edt_name.text.toString()
            word.desc = edt_desc.text.toString() + " time At:  " + System.currentTimeMillis()
            wordViewModel?.insert(word)
            finish()
        } else
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
