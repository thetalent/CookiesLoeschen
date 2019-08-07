package com.falkhiller.myapplication

import android.app.usage.UsageEvents
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val goodStates = listOf(listOf(0,0,0),
        listOf(0,1,1),
        listOf(0,2,2),
        listOf(0,3,3),
        listOf(1,0,1),
        listOf(1,1,0),
        listOf(1,2,3),
        listOf(1,3,2),
        listOf(2,0,2),
        listOf(2,1,3),
        listOf(2,2,0),
        listOf(2,3,1),
        listOf(3,0,3),
        listOf(3,1,2),
        listOf(3,2,1),
        listOf(3,3,0),
        listOf(4,4,0),
        listOf(5,4,1))
    var status = mutableMapOf("r1" to 5,"r2" to 4, "r3" to 3)
    var currentTag = "0"
    var butchecked = mutableListOf<Button>()
    fun checkOut(v: View){
        butchecked.forEach { it.isClickable = false
        it.visibility = View.INVISIBLE
        status[it.tag.toString()]  =   status[it.tag.toString()]!! - 1
        }
        currentTag = "0"
        butchecked.removeAll{it->true}
        if(status["r1"] == 0 && status["r2"] == 0 && status["r3"] == 0) {
            Toast.makeText(this, "You Won", Toast.LENGTH_LONG).show()
            nochmal.visibility = View.VISIBLE
        }
        player2()

    }


    fun player2(){
        var todo = true
        var actStatus = listOf(status["r1"],status["r2"], status["r3"] )
        goodStates.forEach {
            if (it[0] == actStatus[0] && it[1] == actStatus[1] && it[2] < actStatus[2]!!){
                take(2, actStatus[2]!! - it[2])
                todo = false
                return@player2
            }
            if (it[1] == actStatus[1] && it[2] == actStatus[2] && it[0] < actStatus[0]!!){
                take(0,actStatus[0]!! -it[0])
                todo = false
                return@player2
            }
            if (it[2] == actStatus[2] && it[0] == actStatus[0] && it[1] < actStatus[1]!!){
                take(1, actStatus[1]!! - it[1])
                todo = false
                return@player2
            }
        }

        if(todo){

        }

    }


    fun reload(v:View){
        for ( i in 0..4) {
            (row0.getChildAt(i) as Button).apply{
                this.isClickable = true
                this.visibility = View.VISIBLE
                this.text = ""
            }
        }
        for ( i in 0..3) {
            (row1.getChildAt(i) as Button).apply{
                this.isClickable = true
                this.visibility = View.VISIBLE
                this.text = ""
            }
        }
        for ( i in 0..2) {
            (row2.getChildAt(i) as Button).apply{
             isClickable = true
                visibility = View.VISIBLE
                this.text = ""
            }
        }
        v.visibility = View.INVISIBLE
        status = mutableMapOf("r1" to 5,"r2" to 4, "r3" to 3)
    }
    fun take(row:Int, amount:Int){
        var token = 0
        var takeRow =cookies.getChildAt(row ) as LinearLayout
        for(i in 0..4-row){
            var but = takeRow.getChildAt(i)
            if(but.visibility == View.VISIBLE){
                but.visibility = View.INVISIBLE
                but.isClickable = false
                token += 1
                status["r" + (row + 1).toString()] =  status["r" + (row +1).toString()]!! - 1
                if(status["r1"] == 0 && status["r2"] == 0 && status["r3"] == 0){
                    Toast.makeText(this,"You Lost",Toast.LENGTH_LONG).show()
                    nochmal.visibility = View.VISIBLE
                }
                if(token == amount){return}

            }

    }

    }
    fun mark(v: View){
    // click event for button
        val but = v as Button
    // Der Tag eines Buttons ist seine Reihe
        if(currentTag == but.tag ) {
            but.text = getString(R.string.marked)
            butchecked.add(but)
        }
        else {
            if (currentTag == "0") {
                but.text =getString(R.string.marked);currentTag = but.tag.toString();
                butchecked = mutableListOf(but)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
