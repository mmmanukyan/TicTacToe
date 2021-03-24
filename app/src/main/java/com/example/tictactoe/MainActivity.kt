package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cl = findViewById<ConstraintLayout>(R.id.cl)

        var n=0

        for (i in 0 until cl.childCount) {
            val v = cl.getChildAt(i)
            v.setOnClickListener {
                if(it.id==R.id.rb)
                {
                    n=0
                    for (i in 0 until cl.childCount) {
                        val b = cl.getChildAt(i)
                        if(b is Button && b!=it)
                        {
                            b.text = ""
                        }
                    }
                }
                else if(it is Button && it.text == "") {
                    val symbol = if (n %2 ==0) "X" else "O"
                    it.text = symbol
                    var isWin = false
                    val wins = Array(5){ _->true}
                    for(y in 0..2){
                        var isHorizontal = true
                        for(x in 0..2) {
                            val i = y * 3 + x
                            if(i < cl.childCount){
                                val b = cl.getChildAt(i)
                                if (b is Button &&b.text != symbol) {
                                    isHorizontal = false
                                    wins[x] = false
                                    if (x == y) wins[3] = false
                                    if (x + y == 2) wins[4] = false
                                }
                            }
                        }
                        if(isHorizontal){
                            isWin = true
                            break
                        }
                    }
                    n++
                    if(isWin || wins.any { w->w }) {
                        Toast.makeText(this, "$symbol won", Toast.LENGTH_SHORT).show()
                    }else if(n==9){
                        Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }
}