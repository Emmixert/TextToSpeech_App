package com.example.attempt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.TextView
import android.widget.Button
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener
{
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}


        var message: String = findViewById<TextView>(R.id.tvStatus).text.toString()
        Log.i("Message TextView", message)
    }

    private fun speak()
    {
        var message: String=findViewById<TextView>(R.id.etMessage).text.toString()

        if(message.isEmpty())
        {
            findViewById<TextView>(R.id.etMessage).text="Introduzca un texto"
            message="¿En serio?, Escribe algo en el EditText"
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int)
    {
        if(status==TextToSpeech.SUCCESS)
        {
            findViewById<TextView>(R.id.tvStatus).text="¡Hola Kotlin! :)"
            tts!!.setLanguage(Locale("ES"))
        }
        else
        {
            findViewById<TextView>(R.id.tvStatus).text="No disponible :("
        }
    }

    override fun onDestroy()
    {
        if(tts!=null)
        {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}