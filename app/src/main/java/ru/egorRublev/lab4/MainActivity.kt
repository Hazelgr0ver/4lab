package ru.egorRublev.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etFirst: EditText
    private lateinit var etSecond: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirst = findViewById(R.id.etFirst)
        etSecond = findViewById(R.id.etSecond)
        tvResult = findViewById(R.id.tvResult)

        val btAdd: Button = findViewById(R.id.btAdd)
        val btMin: Button = findViewById(R.id.btMin)
        val btAnd: Button = findViewById(R.id.btAnd)
        val btDel: Button = findViewById(R.id.btDel)

        btAdd.setOnClickListener { calculate('+') }
        btMin.setOnClickListener { calculate('-') }
        btAnd.setOnClickListener { calculate('*') }
        btDel.setOnClickListener { calculate('/') }

        // Установка слушателя для отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculate(operation: Char) {
        val firstNumber = etFirst.text.toString().toFloatOrNull()
        val secondNumber = etSecond.text.toString().toFloatOrNull()

        if (firstNumber == null || secondNumber == null) {
            tvResult.text = "Введите корректные числа"
            return
        }

        val result = when (operation) {
            '+' -> firstNumber + secondNumber
            '-' -> firstNumber - secondNumber
            '*' -> firstNumber * secondNumber
            '/' -> if (secondNumber != 0.0f) firstNumber / secondNumber else "Деление на ноль!"
            else -> 0.0
        }

        tvResult.text = when {
            result != null -> "$firstNumber $operation $secondNumber = $result"
            operation == '/' -> "$firstNumber $operation $secondNumber = Деление на ноль!"
            else -> "Ошибка"
        }
    }
}