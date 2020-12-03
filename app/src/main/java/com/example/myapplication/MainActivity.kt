package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Compiler.disable

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board: Array<Array<Button>>
    var turn = true
    var c = 0
    var xo = Array(3) {IntArray(3)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for(i in board)
        {
            for(button in i)
                button.setOnClickListener(this)
        }

        initializexo()

        reset.setOnClickListener {
            turn = true
            c = 0
            player.text = "Player X's Turn"
            initializexo()
        }
    }

    private fun initializexo() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                xo[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id)
        {
            R.id.button1 -> {
                update(row = 0, col = 0, t = turn)
            }
            R.id.button2 -> {
                update(row = 0, col = 1, t = turn)
            }
            R.id.button3 -> {
                update(row = 0, col = 2, t = turn)
            }
            R.id.button4 -> {
                update(row = 1, col = 0, t = turn)
            }
            R.id.button5 -> {
                update(row = 1, col = 1, t = turn)
            }
            R.id.button6 -> {
                update(row = 1, col = 2, t = turn)
            }
            R.id.button7 -> {
                update(row = 2, col = 0, t = turn)
            }
            R.id.button8 -> {
                update(row = 2, col = 1, t = turn)
            }
            R.id.button9 -> {
                update(row = 2, col = 2, t = turn)
            }
        }

        c++
        turn = !turn
        if(turn)
        {
            updateDisplay("Player X's Turn")
        }
        else
        {
            updateDisplay("Player O's Turn")
        }

        if(c == 9)
            updateDisplay("Game Draw")

        checkWinner()
    }

    private fun checkWinner() {
        for(i in 0..2)
        {
            if(xo[i][0] == xo[i][1] && xo[i][0] == xo[i][2])
            {
                if(xo[i][0] == 1)
                {
                    updateDisplay("Player X Wins")
                    disable()
                    break
                }
                else if(xo[i][0] == 0)
                {
                    updateDisplay("Player O Wins")
                    disable()
                    break
                }
            }
        }

        for(i in 0..2)
        {
            if(xo[0][i] == xo[1][i] && xo[0][i] == xo[2][i])
            {
                if(xo[0][i] == 1)
                {
                    updateDisplay("Player X Wins")
                    disable()
                    break
                }
                else if(xo[0][i] == 0)
                {
                    updateDisplay("Player O Wins")
                    disable()
                    break
                }
            }
        }

        if(xo[0][0] == xo[1][1] && xo[0][0] == xo[2][2])
        {
            if(xo[0][0] == 1)
            {
                updateDisplay("Player X Wins")
                disable()
            }
            else if(xo[0][0] == 0)
            {
                updateDisplay("Player O Wins")
                disable()
            }
        }

        if(xo[0][2] == xo[1][1] && xo[0][2] == xo[2][0])
        {
            if(xo[1][1] == 1)
            {
                updateDisplay("Player X Wins")
                disable()
            }
            else if(xo[1][1] == 0)
            {
                updateDisplay("Player O Wins")
                disable()
            }
        }
    }

    private fun disable()
    {
        for(i in board)
        {
            for(button in i)
                button.isEnabled = false
        }
    }

    private fun updateDisplay(s: String) {
        player.text = s
    }

    private fun update(row: Int, col: Int, t: Boolean) {
        val text = if(t) "X" else "O"
        val value = if(t) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        xo[row][col] = value
    }
}