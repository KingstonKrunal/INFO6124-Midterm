package com.example.krunalshah.krunaldogpound

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DogPoundActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var myList = ArrayList<String?>()
    private val data = ArrayList<DogsViewModel>()
    private val adapter = myAdapter(data)
    private var message: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_pound)

        message = intent.getStringExtra("message")

        myList = getArrayPrefs(R.string.dog_list.toString(), this)
        myList.add(message)

        myList.forEach {
            if (it!=null) {
                data.add(DogsViewModel(it))
            }
        }
        adapter.notifyDataSetChanged()

        val mainActivityButton: Button = findViewById(R.id.mainActivityButton)
        mainActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val count: String = myList.size.toString()
            intent.putExtra("countMessage", count)
            startActivity(intent)
        }

        val recyclerview = findViewById<RecyclerView>(R.id.dogPoundRecyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        message = intent.getStringExtra("message")

        myList = getArrayPrefs(R.string.dog_list.toString(), this)
        myList.add(message)

        myList.forEach {
            if (it!=null) {
                data.add(DogsViewModel(it))
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun setArrayPrefs(
        arrayName: String,
        array: ArrayList<String?>,
        mContext: Context
    ) {
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(arrayName + "_size", array.size)

        for (i in 0 until array.size) editor.putString(arrayName + "_" + i, array[i])
        editor.apply()
    }

    private fun getArrayPrefs(arrayName: String, mContext: Context): ArrayList<String?> {
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val size = prefs.getInt(arrayName + "_size", 0)
        val array: ArrayList<String?> = ArrayList(size)

        for (i in 0 until size) array.add(prefs.getString(arrayName + "_" + i, null))

        return array
    }
}
