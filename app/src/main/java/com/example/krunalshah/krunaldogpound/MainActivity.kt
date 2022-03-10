package com.example.krunalshah.krunaldogpound

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var myList = ArrayList<String?>()
    private val data = ArrayList<DogsViewModel>()
    private val adapter = myAdapter(data)
    private var message: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = ArrayList<DogsViewModel>()
        val adapter = myAdapter(data)

        val dogNameText: EditText = findViewById(R.id.dogNameText)
        val checkBox: CheckBox = findViewById(R.id.houseTrainedCheckBox)
        val totalDogsValue: TextView = findViewById(R.id.totalDogsValueTextView)
        val dogPoundButton: Button = findViewById(R.id.addDogToPoundButton)
        var count: String

        val extras = intent.extras
        count = extras?.getString("countMessage").toString()
        totalDogsValue.text = adapter.itemCount.toString()

        dogPoundButton.setOnClickListener {
            val intent = Intent(this, DogPoundActivity::class.java)

            val dogName: String = dogNameText.text.toString()
            val checkBoxString: String

            if (checkBox.isChecked) {
                checkBoxString = " is House Trained"
            } else {
                checkBoxString = " is not House Trained"
            }

            val listMessage: String = "Dog Pound: $dogName$checkBoxString"

            intent.putExtra("message", listMessage)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        data.clear()
        myList.clear()
        adapter.notifyDataSetChanged()
    }
}