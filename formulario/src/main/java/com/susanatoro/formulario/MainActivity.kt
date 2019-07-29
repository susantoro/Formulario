package com.susanatoro.formulario

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var fechanacimiento = ""
    private var ciudad = ""
    private var nombre = ""
    private var password = ""
    private var repassword = ""
    private var correo = ""
    private var hobbies = ""
    private var sexo = ""
    private var hobbies_respaldo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ciudades = arrayOf("Medellín", "Bogotá", "Cali", "Barranquilla", "Manizales")
        spnCiudades.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ciudades)
        spnCiudades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                ciudad = "Elige ciudad de nacimiento"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ciudad = ciudades[p2]
            }

        }

    }

    fun ButtonClick(view: View) {

        nombre = etNombre.text.toString()
        password = etPass.text.toString()
        repassword = etRepass.text.toString()
        correo = etCorreo.text.toString()

        hobbies =""

        if(chLectura.isChecked)
            hobbies += chLectura.text.toString()+ ", "
        if(chDeportes.isChecked)
            hobbies += chDeportes.text.toString()+ ", "
        if(chCocinar.isChecked)
            hobbies += chCocinar.text.toString()+ ", "
        if(chRedes.isChecked)
            hobbies += chRedes.text.toString()+ ", "


        if (nombre != "" && password != "" && repassword != "" && correo!="" && hobbies!="" && sexo!="" && fechanacimiento!="" && ciudad!=""){
            if(password==repassword) {
                tvResultado.text = "Nombre: " + nombre + "\n" + "Contraseña: " + password + "\n" + "Contraseña2: "+ repassword + "\n" + "Correo: " + correo + "\n" + "Hobbies: "+ hobbies + "\n" +"Sexo: "+ sexo + "\n" + "Fecha de Nacimiento: "+fechanacimiento + "\n" +"Ciudad: " +ciudad
                hobbies = ""

            }
            else {
                Toast.makeText(this,"Las contraseñas son diferentes",Toast.LENGTH_SHORT).show()
                tvResultado.text = ""
            }

        }else {
            Toast.makeText(this,"Uno o más campos están vacíos",Toast.LENGTH_SHORT).show()
            tvResultado.text = ""
        }


    }


    fun onClickDatePicker(view: View) {

        val cal = Calendar.getInstance()
        val anio = cal.get(Calendar.YEAR)
        val mes = cal.get(Calendar.MONTH)
        val dia = cal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

            fechanacimiento = "dia: $day / mes: ${month + 1} / año: $year "
            tvFechaNacimiento.text = fechanacimiento
        }
        DatePickerDialog(this, dpd, anio, mes, dia).show()
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            when (view.getId()) {

                R.id.rbFemenino ->
                    if (checked) {
                        sexo = rbFemenino.text.toString()
                    }
                R.id.rbMasculino ->
                    if (checked) {
                        sexo = rbMasculino.text.toString()
                    }
            }

        }

    }
}


