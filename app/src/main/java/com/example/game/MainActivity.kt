package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        playagainbttn.visibility =
            View.INVISIBLE //start code with invisible play again button (watch video carefully to understand why)

        enterbttn.setOnClickListener {
            //TODO
        }
    }
}


//Hint-1: If you want to swipe from activity to another after losing or winning you can use the following code
    // val intent = Intent(this, MainActivity::class.java)
    //                    startActivity(intent)

//Hint-2: Importing (kotlinx.android.synthetic.main.activity_main.*) makes it easy to use the components from the UI by calling it according to its name id like (playagainbttn).

//Hint-3: Use (AlertDialog.Builder(this)) to show some warning alert (google it to know how to use it).
