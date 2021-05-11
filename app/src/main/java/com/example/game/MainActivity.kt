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
        rand = (1..10).random()
        enterbttn.setOnClickListener {
            val x = number.text.toString().toIntOrNull() ?: 0
            if (x !in 1..10) showAlert("Number must be 1 and 10")
            if (tryCount > 1)
            {
                tryCount -= 1
                if (x == rand)
                {
                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)
                }
                else
                {
                    showAlert("try Again You have ${tryCount - 1} more chance")
                }
            }
            else
            {
                val view: View = currentFocus ?: View(this)
                (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                    hideSoftInputFromWindow(view.windowToken, 0)
                }
                playagainbttn.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
                number.visibility = View.INVISIBLE
                enterbttn.visibility = View.INVISIBLE
                textView2.visibility = View.INVISIBLE
                Toast.makeText(this,"No more attempts",Toast.LENGTH_SHORT).show()
            }
        }

        playagainbttn.setOnClickListener {
            playagainbttn.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE
            number.visibility = View.VISIBLE
            enterbttn.visibility = View.VISIBLE
            textView2.visibility = View.VISIBLE
            number.setText("")
            tryCount = 3
            rand = (1..10).random()
        }
    }

    fun showAlert(msg: String) {
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
