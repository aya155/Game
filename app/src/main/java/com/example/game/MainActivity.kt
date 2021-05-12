package com.example.game

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    var rand : Int = 0
    var tryCount = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playagainbttn.visibility = View.INVISIBLE //start code with invisible play again button (watch video carefully to understand why)
        rand = (1..10).random() //Generate Random number between 1 and 10
        enterbttn.setOnClickListener {
            val x = number.text.toString().toIntOrNull() ?: 0 // Convert user input to double
            if (x !in 1..10) {
                showAlert("Number must be 1 and 10")
                return@setOnClickListener // If number not in required range, ask user to input new number in it.
            }

            if (tryCount > 1) //If user have attempts
            {
                tryCount -= 1 //Decrease  user attempts by one
                if (x == rand) //If user input equal to random number
                {
                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)// Show congratulation activity
                }
                else
                {
                    showAlert("try Again You have $tryCount more chance") //Tell user his left attempts' count
                }
            }
            else // when user fail to guess number
            {
                //Hide Keyboard
                val view: View = currentFocus ?: View(this)
                (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                    hideSoftInputFromWindow(view.windowToken, 0)
                }

                playagainbttn.visibility = View.VISIBLE //Show play again button


                //Hide other views
                textView.visibility = View.INVISIBLE
                number.visibility = View.INVISIBLE
                enterbttn.visibility = View.INVISIBLE
                textView2.visibility = View.INVISIBLE


                Toast.makeText(this,"No more attempts",Toast.LENGTH_SHORT).show() //Tell user he consume all his attempts


            }
        }

        playagainbttn.setOnClickListener {
            //Hide play again button
            playagainbttn.visibility = View.INVISIBLE

            //Show other views
            textView.visibility = View.VISIBLE
            number.visibility = View.VISIBLE
            enterbttn.visibility = View.VISIBLE
            textView2.visibility = View.VISIBLE

            //Reinitialize
            number.setText("")
            tryCount = 3
            rand = (1..10).random()
        }
    }

    //Function to create alert by AlertDialog with arbitrary message
    private fun showAlert(msg: String) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("Try Again")
        alertDialog.setMessage(msg)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue")
        { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }
}


//Hint-1: If you want to swipe from activity to another after losing or winning you can use the following code
    // val intent = Intent(this, MainActivity::class.java)
    //                    startActivity(intent)

//Hint-2: Importing (kotlinx.android.synthetic.main.activity_main.*) makes it easy to use the components from the UI by calling it according to its name id like (playagainbttn).

//Hint-3: Use (AlertDialog.Builder(this)) to show some warning alert (google it to know how to use it).
