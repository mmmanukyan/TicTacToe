package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tictactoe.databinding.FragmentDiceRollerBinding
import com.example.tictactoe.databinding.FragmentMainBinding

class DiceRoller : Fragment() {
    private lateinit var binding: FragmentDiceRollerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiceRollerBinding.inflate(inflater, container, false)
        binding.btn.setOnClickListener {
            val r = 1..6
            val v1 = r.random()
            updateImage(binding.imageView1, v1)
            val v2 = r.random()
            updateImage(binding.imageView2, v2)
            if (v1 != v2)
                (if (v1 > v2) Players.player1 else Players.player2).point++
            updateText()
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        updateText()
    }
    private fun updateImage(img:ImageView,i:Int) {
        img.setImageResource(
            when (i) {
                1 -> R.drawable.d1
                2 -> R.drawable.d2
                3 -> R.drawable.d3
                4 -> R.drawable.d4
                5 -> R.drawable.d5
                else -> R.drawable.d6
            }
        )
    }
    private fun updateText(){
        binding.tv.text = "${Players.player1.name} : ${Players.player1.point} , ${Players.player2.name} : ${Players.player2.point}"
    }
}