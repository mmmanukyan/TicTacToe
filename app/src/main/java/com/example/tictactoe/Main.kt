package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.FragmentMainBinding

class Main : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.ttt.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fl, TicTacToe())?.addToBackStack(null)?.commit()
        }
        binding.dr.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.fl, DiceRoller())?.addToBackStack(null)?.commit()
        }
        binding.names.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.fl, EditNames())?.addToBackStack(null)?.commit()
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        binding.pl1.text = "${Players.player1.name} : ${Players.player1.point}"
        binding.pl2.text = "${Players.player2.name} : ${Players.player2.point}"
    }
}