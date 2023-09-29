package com.example.gagarinov_pr_33_prac20

import android.bluetooth.le.AdvertiseSettings
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.gagarinov_pr_33_prac20.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var standart : Button
    lateinit var call : Button
    lateinit var custom : Button
    lateinit var floating_btn: FloatingActionButton
    lateinit var count_tw:TextView

    private val APP_PREFERENCES="my_settings"
    private val PREF_COUNT="Count_press"

    lateinit var settings: SharedPreferences

    var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        standart = findViewById(R.id.standart_btn)
        call = findViewById(R.id.custom_btn)
        custom = findViewById(R.id.not_standart_btn)
        floating_btn = findViewById(R.id.float_btn)
        count_tw=findViewById(R.id.count_tw)

        settings=getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

        count=settings.getInt(PREF_COUNT,0)
        count_tw.text=count.toString()

        standart.setOnClickListener{
            count_tw.text=""

            val snack3 = Snackbar.make(it,"Самый обычный Snackbar",Snackbar.LENGTH_LONG)
            snack3.show()
        }

        call.setOnClickListener{
            count_tw.text=""

            val snack = Snackbar.make(it,"Вы изменили что-то",Snackbar.LENGTH_LONG)
            snack.setAction("Вернуть как было?", View.OnClickListener
            {
                val snack1 = Snackbar.make(it,"Все вернулось на свои места",Snackbar.LENGTH_LONG)
                snack1.show()
            })
            snack.show()
        }

        custom.setOnClickListener{
            count_tw.text=""

            val snack = Snackbar.make(it,"Повторите еще раз",Snackbar.LENGTH_LONG)
            snack.setActionTextColor(Color.YELLOW)
            snack.setBackgroundTint(Color.MAGENTA)
            snack.setTextColor(Color.YELLOW);
            snack.setAction("Повторить", View.OnClickListener
            {
                val snack1 = Snackbar.make(it,"Все вернулось на свои места",Snackbar.LENGTH_LONG)
                snack1.show()
            })
            snack.show()
        }

        floating_btn.setOnClickListener{
            count_tw.text=""

            val snack = Snackbar.make(it,"Вы нажали на FloatingActionButton",Snackbar.LENGTH_LONG)
            snack.show()

            count+=1
        }
    }

    override fun onStop() {
        val editor=settings.edit()
        editor.putInt(PREF_COUNT,count)
        editor.apply()
        super.onStop()
    }
}