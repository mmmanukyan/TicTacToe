package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tictactoe.databinding.FragmentMainBinding
import com.example.tictactoe.databinding.FragmentTicTacToeBinding

class TicTacToe : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentTicTacToeBinding.inflate(inflater, container, false)
        val cl = binding.root

        var n=0
        var is1 = true

        val updateText : ()->Unit= {
            val isx = n % 2 == 0
            binding.tv.text =
                "${(if (is1.xor(isx)) Players.player2 else Players.player1).name} select ${if (isx) "X" else "O"}"
        }
        for (i in 0 until cl.childCount) {
            val v = cl.getChildAt(i)
            v.setOnClickListener {
                if(it == binding.rb)
                {
                    n=0
                    is1 =!is1
                    for (i in 0 until cl.childCount) {
                        val b = cl.getChildAt(i)
                        if(b is Button && b!=it)
                        {
                            b.text = ""
                        }
                    }
                    updateText()
                }else if(n==9) {
                    return@setOnClickListener
                } else if(it is Button && it.text == "") {
                    val isx = n%2==0
                    val symbol = if (isx) "X" else "O"
                    it.text = symbol
                    var isWin = false
                    val wins = Array(5){ _->true}
                    for(y in 0..2){
                        isWin = true
                        for(x in 0..2) {
                            val i = y * 3 + x
                            if(i < cl.childCount){
                                val b = cl.getChildAt(i)
                                if (b is Button &&b.text != symbol) {
                                    isWin = false
                                    wins[x] = false
                                    if (x == y) wins[3] = false
                                    if (x + y == 2) wins[4] = false
                                }
                            }
                        }
                        if(isWin){
                            break
                        }
                    }
                    n++
                    if(isWin || wins.any { w->w }) {
                        val player = if (is1.xor(isx)) Players.player2 else Players.player1
                        player.point++
                        binding.tv.text = "${player.name} won"
                        n = 9
                    }else if(n==9){
                        binding.tv.text = "draw"
                    }else{
                        updateText()
                    }
                }
            }
        }
        updateText()
        return cl
    }

}