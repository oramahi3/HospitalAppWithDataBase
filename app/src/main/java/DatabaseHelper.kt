

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


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
      val query = ("CREATE TABLE" + Patients + "(" + PatientSSNO +" INT PRIMARYKEY , " + PatientName + "TEXT" + DEPNAME + " TEXT" + DoctorName + "TEXT" +")")
      db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    db?.execSQL("DROP TABLE IF EXISTS" + Patients)
        onCreate(db)
    }
fun insertPatient(PName:String , PSSNO:Int , DNAME : String , DocNAME:String)
{
    val values = ContentValues()
    values.put(PatientName,PName)
    values.put(PatientSSNO,PSSNO)
    values.put(DEPNAME,DNAME)
   values.put(DoctorName,DocNAME)
    val db = this.writableDatabase
    db.insert(Patients,null,values)
   //Toast.makeText(this,"Patient Inserted Successfully",Toast.LENGTH_SHORT).show()
    db.close()
}

    fun getPatients():Cursor?
    {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM "+Patients, null)
    }
}