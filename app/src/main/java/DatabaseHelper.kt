

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.example.project.model.patientsmodel


class DatabaseHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, Patients, factory, DATABASE_VERSION) {



    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "PatientData"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val Patients = "TABLE_NAME"

        // below is the variable for id column
        val PatientSSNO = "ID"

        // below is the variable for name column
        val PatientName = "name1"

        // below is the variable for age column
        val DoctorName = "name2"
        val DEPNAME = "name3"
    }


    override fun onCreate(db: SQLiteDatabase?) {
      val query = ("CREATE TABLE" + Patients + "(" + PatientSSNO +" INT PRIMARY KEY , " + PatientName + "TEXT" + DEPNAME + " TEXT" + DoctorName + "TEXT" +")")
      db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    val DROP_TABLE = "DROP TABLE IF EXISTS $Patients"
        db?.execSQL(DROP_TABLE)
    }
//fun insertPatient(PName:String , PSSNO:Int , DNAME : String , DocNAME:String)
//{
//    val values = ContentValues()
//    values.put(PatientName,PName)
//    values.put(PatientSSNO,PSSNO)
//    values.put(DEPNAME,DNAME)
//   values.put(DoctorName,DocNAME)
//    val db = this.writableDatabase
//    db.insert(Patients,null,values)
//   //Toast.makeText(this,"Patient Inserted Successfully",Toast.LENGTH_SHORT).show()
//    db.close()
//
fun insertpatient (patient:patientsmodel):Boolean
{
    val db = this.writableDatabase
    val values = ContentValues()
    values.put(DoctorName,patient.docname)
    values.put(DEPNAME,patient.debname)
    values.put(PatientName,patient.pname)
    val success = db.insert(Patients,null,values)
    db.close()
    return (Integer.parseInt("$success") != -1 )
}

    fun getPatients():List<patientsmodel> {

        val patientlist = ArrayList<patientsmodel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $Patients"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null)
            if (cursor.moveToFirst()) {
                do {
                    val patients = patientsmodel()
                    patients.pssno =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(PatientSSNO)))
                    patients.pname = cursor.getString(cursor.getColumnIndexOrThrow(PatientName))
                    patients.debname = cursor.getString(cursor.getColumnIndexOrThrow(DEPNAME))
                    patients.docname = cursor.getString(cursor.getColumnIndexOrThrow(DoctorName))
                    patientlist.add(patients)
                } while (cursor.moveToNext())

        }
        cursor.close()
        return patientlist
    }

    fun selectpatient(pssnumber : Int): patientsmodel
    {
        val patients = patientsmodel()
        val db = writableDatabase
        val selectQuery = "Select * FROM $Patients WHERE $PatientSSNO = $pssnumber"
        val cursor = db.rawQuery(selectQuery,null)
        cursor?.moveToFirst()
        patients.debname = cursor.getString(cursor.getColumnIndexOrThrow(DEPNAME))
        patients.docname = cursor.getString(cursor.getColumnIndexOrThrow(DoctorName))
        patients.pssno = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(PatientSSNO)))
        patients.pname = cursor.getString(cursor.getColumnIndexOrThrow(PatientName))
        cursor.close()
        return patients
    }
    fun deletepatient(pssno:Int):Boolean
    {
        val db = this.writableDatabase
        val success = db.delete(Patients, PatientSSNO+"?=", arrayOf(pssno.toString()))
        return Integer.parseInt("$success") != -1
    }
    fun updatepatientsdata(patients:patientsmodel):Boolean
    {
        val db = this.writableDatabase
        val values=ContentValues()
        values.put(Patients,patients.pname)
        values.put(Patients,patients.docname)
        values.put(Patients,patients.debname)
        val success = db.update(Patients,values, PatientSSNO +"=?", arrayOf(patients.pssno.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
    }