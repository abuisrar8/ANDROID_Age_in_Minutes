package com.example.ageinmin

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn  = findViewById<Button>(R.id.btnDatePicker)
        btn.setOnClickListener{view ->
            ClickDatePicker(view)
           // Toast.makeText(this,"Button Works ",Toast.LENGTH_SHORT).show()
        }


    }


    fun ClickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfWeek = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this)
        var DpD = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
            view, Year, Month, DayOfMonth ->


                    val  SelectedDate = "$DayOfMonth/${Month+1}/$Year"
                    val DateSelected = findViewById<TextView>(R.id.DateSelected)
                    DateSelected.setText(SelectedDate)

                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                    val theDate = sdf.parse(SelectedDate)
                    val currentDate = sdf.parse(sdf.format(Date()))
                    var alldays = ((abs(currentDate!!.time-theDate!!.time) / (24 * 60 * 60 * 1000))).toString()
                    var days = ((abs(currentDate.time-theDate.time) / (24 * 60 * 60 * 1000))%31).toString()
                    var months =((
                            (kotlin.math.abs(
                                    currentDate.time - theDate.time) / ( 24 * 60 * 60 * 1000))
                            )
                            /30).toString()
                    var years =((abs(currentDate.time-theDate.time) / (24 * 60 * 60 * 1000))/365).toString()

                    val daysLived = findViewById<TextView>(R.id.daysLived)
                    daysLived.setText((abs((currentDate.time - theDate.time)/(60*1000) )).toString())
                    Toast.makeText(
                            this,
                            "\noHHh!!! YOu LiVED A LoT !!! \n\nWiSH YOu LiVE A LoNg LiFE !!!\n",
                            Toast.LENGTH_LONG).show()
                    val ActualAge = findViewById<TextView>(R.id.actualAge)
                    ActualAge.setText("your actual age is \n $years year(s) \n $months month(s) \n $days day(s)")

        },year,month,dayOfWeek)

        DpD.datePicker.setMaxDate(Date().time - 86400000)
        DpD.show()
    }


}