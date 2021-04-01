package com.example.tictactoe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.tictactoe.databinding.FragmentEditNamesBinding
import com.example.tictactoe.databinding.FragmentMainBinding

class EditNames : Fragment() {
    private lateinit var binding: FragmentEditNamesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNamesBinding.inflate(inflater, container, false)
        binding.pl1.doAfterTextChanged {
            Players.player1.name =
                if (it == null || it.toString().isEmpty()) "Player1" else it.toString()
        }
        binding.pl2.doAfterTextChanged {
            Players.player2.name =
                if (it == null || it.toString().isEmpty()) "Player2" else it.toString()
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        binding.pl1.setText(Players.player1.name)
        binding.pl2.setText(Players.player2.name)
    }
}