package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoTextView.text = "0"
        operacion = SIN_OPERACION

        unoButton.setOnClickListener { numberPressed("1") }
        dosButton.setOnClickListener { numberPressed("2") }
        tresButton.setOnClickListener { numberPressed("3") }
        cuatroButton.setOnClickListener { numberPressed("4") }
        cincoButton.setOnClickListener { numberPressed("5") }
        seisButton.setOnClickListener { numberPressed("6") }
        sieteButton.setOnClickListener { numberPressed("7") }
        ochoButton.setOnClickListener { numberPressed("8") }
        nueveButton.setOnClickListener { numberPressed("9") }
        ceroButton.setOnClickListener { numberPressed("0") }
        puntoButton.setOnClickListener { numberPressed(".") }

        borrarButton.setOnClickListener { resetAll() }

        sumaButton.setOnClickListener { operationPressed(SUMA) }
        restaButton.setOnClickListener { operationPressed(RESTA) }
        multiplicacionButton.setOnClickListener { operationPressed(MULTIPLICACION) }
        divisionButton.setOnClickListener { operationPressed(DIVISION) }

        igualButton.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(resultadoTextView.text == "0" && num != ".") {
            resultadoTextView.text = "$num"
        } else {
            resultadoTextView.text = "${resultadoTextView.text}$num"
        }

        if(operacion == SIN_OPERACION){
            num1 = resultadoTextView.text.toString().toDouble()
        } else {
            num2 = resultadoTextView.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int){
        this.operacion = operacion
        num1 = resultadoTextView.text.toString().toDouble()

        resultadoTextView.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        resultadoTextView.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun resetAll(){
        resultadoTextView.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val SIN_OPERACION = 0
    }
}