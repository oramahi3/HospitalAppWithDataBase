package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var bVisited: Boolean = false
        var visit: RadioGroup = findViewById(R.id.visitGrp)
        visit.setOnCheckedChangeListener { group, checkedId ->
            bVisited = checkedId == R.id.visitYes
        }

        var bInsured: Boolean = false
        var insurance: RadioGroup = findViewById(R.id.insuranceGrp)
        insurance.setOnCheckedChangeListener { group, checkedId ->
            bInsured = checkedId == R.id.insuranceYes
        }



        var x: String = "9 - 10"
        var time: Spinner = findViewById(R.id.spTime)
        var options =
            arrayOf("9 - 10", "10 - 11", "11 - 12", "12 - 1", "1 - 2", "2 - 3", "3 - 4", "4 - 5")
        time.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                x = options.get(p2) //p2 is the index of selected item
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        var y: String = "Check Up"
        var reason: Spinner = findViewById(R.id.spReason)
        var options1 = arrayOf("Consultation", "Check Up", "Follow Up")
        reason.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options1)

        reason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                y = options1.get(p2) //p2 is the index of selected item
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}