package com.example.project

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.example.project.model.patientsmodel


class MainActivity : AppCompatActivity() {
    var dbhandler: DatabaseHelper? = null
    var patientlist: List<patientsmodel> = ArrayList<patientsmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x: String = "Surgery"
        var flag: String
        var flag1: String
        var flag2: String
        var flagGender: String
        dbhandler = DatabaseHelper(this, null)

        val P_NAME: EditText = findViewById(R.id.editName)
        val P_SSNO: EditText = findViewById(R.id.editIdNum)
        val spinnerDept: Spinner = findViewById(R.id.spChooseDept)
        var Dept = arrayOf(
            "Surgery" /*1200*/, "Orthopaedics"/*250*/, "Neurology", "Plastic Surgery",
            "Orthopaedic", "Cardiology"
        )
        spinnerDept.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Dept)
        spinnerDept.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = Dept.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val spinnerDr: Spinner = findViewById(R.id.spChooseDr)

        if (x == "Surgery") {
            var x1 = arrayOf("Dr. Mike Ross", "Dr. Emma Watson", "Dr. Bob King")
            spinnerDr.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, x1)
            spinnerDr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    flag1 = x1.get(p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Empty
                }
            }
        } else if (x == "Orthopaedics") {
            var x2 = arrayOf("Dr. Harvey Specter", "Dr. Tomas ", "Dr. Lama Ahmad")
            spinnerDr.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, x2)
            spinnerDr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    flag2 = x2.get(p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Empty
                }
            }
        }

        val spinnerGender: Spinner = findViewById(R.id.spGender)
        var Gender = arrayOf("Male", "Female")
        spinnerGender.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Gender)
        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagGender = Dept.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val confirm: Button = findViewById(R.id.btnConfirm)
        confirm.setOnClickListener() {
            val success : Boolean
            val patient : patientsmodel = patientsmodel()
            patient.pname = P_NAME.text.toString()
            patient.pssno = P_SSNO.text.toString().toInt()
            //patient.docname =
            patient.debname = spinnerDept.selectedItem.toString()
            patient.docname = spinnerDr.selectedItem.toString()
            success = dbhandler?.insertpatient(patient) as Boolean
            if(success)
                Toast.makeText(this,"Inserted Successfully" , Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
//        confirm.setOnClickListener()
//        {
//            val name = P_NAME.text.toString()
//            val id = P_SSNO.text.toString().toInt()
//            val d_name = spinnerDept.selectedItem.toString()
//            val doc_name = spinnerDr.selectedItem.toString()
//            db.insertpatient(name,id," "," ")
//            Toast.makeText(this,"Patient Inserted Succesfully",Toast.LENGTH_SHORT).show()
//        }
    }

    private fun getpatients()
    {
     patientlist = dbhandler!!.getPatients()

    }
}
