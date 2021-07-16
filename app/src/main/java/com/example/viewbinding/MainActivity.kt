package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

var Player = true
var TurnCount = 0
lateinit var Board : Array<Array<Button>>
var BoardStatus = Array(3){IntArray(3)}

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Board = arrayOf(
            arrayOf(button, button1, button2),
            arrayOf(button3, button4, button5),
            arrayOf(button6, button7, button8)
        )
        for(i in Board)
        {
            for(button in i )
            {
                button.setOnClickListener(this)
            }
        }
        initializeBoard()
        button9.setOnClickListener{
            initializeBoard()
            Player = true
            TurnCount = 0
            textView.text = "Player X Turn"
        }
    }

    private fun initializeBoard() {
        for( i in 0..2)
        {
            for(j in 0..2)
            {
                BoardStatus[i][j] = -1
            }
        }
        for( i in Board)
        {
            for(button in i)
            {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.button ->
            {
                update(row = 0 , col= 0 , player = Player)
            }
            R.id.button1 ->
            {
                update(row = 0 , col= 1 , player = Player)
            }
            R.id.button2 ->
            {
                update(row = 0 , col= 2 , player = Player)
            }
            R.id.button3 ->
            {
                update(row = 1 , col= 0 , player = Player)
            }
            R.id.button4 ->
            {
                update(row = 1 , col= 1 , player = Player)
            }
            R.id.button5 ->
            {
                update(row = 1 , col= 2 , player = Player)
            }
            R.id.button6 ->
            {
                update(row = 2 , col= 0 , player = Player)
            }
            R.id.button7 ->
            {
                update(row = 2 , col= 1, player = Player)
            }
            R.id.button8 ->
            {
                update(row = 2 , col= 2 , player = Player)
            }
        }
        Player = !Player
        TurnCount++
        if(Player)
        {
            updateDisplay("Player X Turn")
        }
        else
        {
            updateDisplay("Player 0 Turn")
        }
        if(TurnCount == 9)
        {
            textView.text = "Game Draw"
        }
        chechWinner()
    }

    private fun updateDisplay(text: String) {
        textView.text= text
        if(text.contains("Winner"))
        {
            disablebut()
        }
    }

    private fun chechWinner() {
        for (i in 0..2) {
            if (BoardStatus[i][0] == BoardStatus[i][1] && BoardStatus[i][0] == BoardStatus[i][2]) {
                if (BoardStatus[i][0] == 1)
                    updateDisplay("Player X Winner")
                else if(BoardStatus[i][0] == 0)
                    updateDisplay("Player O Winner")
            }
        }
        for (i in 0..2) {
            if (BoardStatus[0][i] == BoardStatus[1][i] && BoardStatus[0][i] == BoardStatus[2][i]) {
                if (BoardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    break
                }
                else if(BoardStatus[0][i] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }

        }
        if(BoardStatus[0][0]== BoardStatus[1][1] && BoardStatus[0][0]== BoardStatus[2][2] )
        {
            if (BoardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")
            }
            else if(BoardStatus[0][0] == 0){
                updateDisplay("Player O Winner")
            }
        }
        if(BoardStatus[0][2]== BoardStatus[1][1] && BoardStatus[0][2]== BoardStatus[2][0] )
        {
            if (BoardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
            }
            else if(BoardStatus[0][2] == 0){
                updateDisplay("Player O Winner")
            }
        }
    }

    private fun disablebut() {
        if(textView.text.contains("Winner"))
            for(i in Board)
            {
                for(button in i)
                {
                    button.isEnabled = false
                }
            }
    }

    private fun update(row: Int, col: Int, player: Boolean) {
        var value = if(player) 1 else 0
        var text = if(player) "X" else "O"
        Board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        BoardStatus[row][col] = value
    }
}