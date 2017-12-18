package com.hi9h_9r0und.lightmeter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var mSensorManager:SensorManager
    lateinit var mSensor:Sensor

    public  var mSensorCount=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager= getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()

        mSensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {

        if(p0?.sensor?.type==Sensor.TYPE_LIGHT)
        {
            mSensorCount++
            var str="Result:"+p0.values[0].toString()+"lux"

            textView_result.text=str
        }
    }

}
