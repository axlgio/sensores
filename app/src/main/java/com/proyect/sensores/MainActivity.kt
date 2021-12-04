package com.proyect.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyect.sensores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor, 0)

        val sensorProxy: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorManager.registerListener(object: SensorEventListener{

            override fun onSensorChanged(event: SensorEvent?) {

                val x = event?.values?.get(0)?:0

                println("pruebaaaaaaaaa  x: $x  ")


                    if(x.toDouble() == 0.0 ){
                        binding.imageV.setImageResource(R.drawable.imagen)
                        println("SI funca")
                    } else{
                        binding.imageV.setImageResource(R.drawable.circulo)
                        println("No funca")
                    }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }, sensorProxy, SensorManager.SENSOR_DELAY_NORMAL)

    }



    override fun onSensorChanged(event: SensorEvent?) {

        val x = event?.values?.get(0)?:0
        val y = event?.values?.get(1)?:0

        if(binding.imageV.x + x.toFloat() > 0 && binding.imageV.x + x.toFloat() < 950){
            binding.imageV.x = binding.imageV.x + x.toFloat()
        }
        if(binding.imageV.y + y.toFloat() > 0 && binding.imageV.y + y.toFloat() < 2000){
            binding.imageV.y = binding.imageV.y + y.toFloat()
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}

