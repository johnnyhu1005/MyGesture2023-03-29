package com.example.mygesture

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnDoubleTapListener
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnGestureListener{

    lateinit var txv:TextView
    lateinit var gDectector:GestureDetector
    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#eeaa00"))
        txv.setBackgroundColor(Color.BLUE)
        txv.typeface = Typeface.createFromAsset(assets,
            "font/HanyiSentyFingerPainting.ttf")
        txv.background.alpha = 50
        gDectector = GestureDetector(this, this)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (txv.text == "手勢辨別"){
            txv.text = "靜宜之美"
        }
        else{
            txv.text = "手勢辨別"
        }
        gDectector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        txv.text = "按下後無後續動作"
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        if (p3 >= 0){
            txv.text = "向上拖曳"
        }
        else{
            txv.text = "向下拖曳"
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        txv.text = "長按"
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        if (p0.x <= p1.x){
            txv.text = "往右快滑"
            count ++
            if(count > 5){
                count = 0
            }
        }
        else{
            txv.text = "往左快滑"
            count--
            if (count < 0){
                count = 5
            }
        }

        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<LinearLayout>(R.id.background).setBackgroundResource(res)
        return true
    }

}