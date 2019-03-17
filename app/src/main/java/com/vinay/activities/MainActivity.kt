package com.vinay.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.vinay.R
import com.vinay.adapters.BaseKotlinAdapter
import com.vinay.architecturecomponent.db.entity.Word
import com.vinay.architecturecomponent.observer.MyObserver
import com.vinay.architecturecomponent.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_row_word.view.*

import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    var wordViewModel: WordViewModel? = null
    private val mListWord = ArrayList<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        lifecycle.addObserver(MyObserver())
        setAdapter()

        wordViewModel?.allWords?.observe(this, Observer { words ->
            mListWord.clear()
            mListWord.addAll(words!!)
            recycler_view.adapter!!.notifyDataSetChanged()
            Log.e(TAG, "onChanged: " + words.size)
        })

    }

    private fun setAdapter() {
        recycler_view.adapter = BaseKotlinAdapter(
            layoutId = R.layout.list_row_word,
            list = mListWord,
            viewHolder = { holder, item ->
                holder.itemView.apply {
                    holder.itemView.txt_name.text = item.word
                    holder.itemView.txt_desc.text = item.desc
                }
            },
            clickableView = R.id.txt_name,
            clickListener = { _, position ->
                onDeleteCall(mListWord[position])
            }
        )
    }

    fun onClick(view: View) {
        startActivity(Intent(this, SaveActivity::class.java))
    }

    private fun onDeleteCall(word: Word) {
        wordViewModel?.delete(word)
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
