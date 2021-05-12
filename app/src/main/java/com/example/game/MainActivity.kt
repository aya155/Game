package com.example.game

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var randomNumber =0
    private var chance=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playagainbttn.visibility = View.INVISIBLE //start code with invisible play again button (watch video carefully to understand why)
        setValues()  // set initial value
        callback()
    }

    private fun callback() {
        enterbttn.setOnClickListener {
            //check if out of range
            if (number.text.toString().toInt() !in 1..10) alert("Warning!!","please enter number in between 1 & 10","OK",ContextCompat.getDrawable(this,R.drawable.ic_baseline_warning_24))
            else{
                //check if value equal number
                if (number.text.toString().toInt() == randomNumber) {
                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)
                } else if (--chance != 0)  // if player has more chance and value is wrong
                    alert("Try Again!!","Try Again! You have $chance more chances","CONTINUE",null)
                else { //if no chance
                    number.text.clear()
                    editVisibility(false)
                }
            }
        }
        playagainbttn.setOnClickListener {
            setValues()
            editVisibility(true)
        }
    }
    // make alert set title and message and button text and drawable ( can be null)
    private fun alert(title:String,message:String,buttonText:String,drawable: Drawable?){
        AlertDialog.Builder(this).apply {
            this.setTitle(title)
            this.setMessage(message)
            this.setPositiveButton(buttonText){_, _ -> }
            this.setIcon(drawable)
        }.create().show()
    }
    // make view visible or invisible
    private fun editVisibility(v:Boolean){
        val c=if(v) View.VISIBLE else View.INVISIBLE
        textView.visibility=c
        textView2.visibility=c
        enterbttn.visibility=c
        number.visibility=c
        playagainbttn.visibility=if(v)View.INVISIBLE else View.VISIBLE
    }
    // set number of chance and new random number
    private fun setValues(){
        chance=3
        randomNumber =(1..10).random().apply{ Log.i("MM","$this")}
    }
}


//Hint-1: If you want to swipe from activity to another after losing or winning you can use the following code
    // val intent = Intent(this, MainActivity::class.java)
    //                    startActivity(intent)

//Hint-2: Importing (kotlinx.android.synthetic.main.activity_main.*) makes it easy to use the components from the UI by calling it according to its name id like (playagainbttn).

//Hint-3: Use (AlertDialog.Builder(this)) to show some warning alert (google it to know how to use it).
