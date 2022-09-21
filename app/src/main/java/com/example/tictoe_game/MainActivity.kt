package com.example.tictoe_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PlAYER=true
    var TURN_COUNT=0
//    var text=""
//    var value=0

    // creatiing matrix board
    var boardStatus=Array(3){IntArray(3) }

    lateinit var board: Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        board= arrayOf(
            arrayOf(button ,button2,button3), arrayOf(button4,button5,button6), arrayOf(button7,button8,button9)

        )
        for(i:Array<Button> in board)
        {
            for(button:Button in i )
            {
                button.setOnClickListener(this)

            }

        }
        initializeBoardStatus()
        resetBtn.setOnClickListener{
            PlAYER=true
            TURN_COUNT=0

            initializeBoardStatus()

        }

    }

    private fun initializeBoardStatus() {
        for (i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j]=-1
            }
        }
        for (i:Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.button->{
                updatValue(row=0,col=0,player=PlAYER)

            }
            R.id.button2->{
                updatValue(row=0,col=1,player=PlAYER)
            }
            R.id.button3->{
                updatValue(row=0,col=2,player=PlAYER)
            }
            R.id.button4->{
                updatValue(row=1,col=0,player=PlAYER)
            }
            R.id.button5->{
                updatValue(row=1,col=1,player=PlAYER)
            }
            R.id.button6->{
                updatValue(row=1,col=2,player=PlAYER)
            }
            R.id.button7->{
                updatValue(row=2,col=0,player=PlAYER)
            }
            R.id.button8->{
                updatValue(row=2,col=1,player=PlAYER)
            }
            R.id.button9->{
                updatValue(row=2,col=2,player=PlAYER)
            }


        }
        TURN_COUNT++
        PlAYER=!PlAYER
        if(PlAYER)
        {
            updateDisplay("Player X turn")
        }
        else
            updateDisplay("Player 0 turn")
        if(TURN_COUNT==9)
        {
            updateDisplay("GAME DRAW")
        }
        checkwinner()

    }

    private fun checkwinner() {

        // horizontal winner check
        for (i in 0..2)
        {
            if (boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2])
            {
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardStatus[i][0]==0)
                {
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }
        // vertical
        for (i in 0..2)
        {
            if (boardStatus[0][i]==boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i])
            {
                if(boardStatus[0][i]==1)
                {
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardStatus[0][i]==0)
                {
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }

        // 1st Digonal
        if (boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2])
        {
            if(boardStatus[0][0]==1)
            {
                updateDisplay("Player X Winner")

            }
            else if(boardStatus[0][0]==0)
            {
                updateDisplay("Player 0 Winner")
            }
        }





        // 2nd Digonal

        if (boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0])
        {
            if(boardStatus[0][2]==1)
            {
                updateDisplay("Player X Winner")

            }
            else if(boardStatus[0][2]==0)
            {
                updateDisplay("Player 0 Winner")
            }
        }

    }

    private fun updateDisplay(s: String) {

        displayTv.text= s
        if(s.contains("Winner"))
        {
            disablebutton()
        }

    }

    private fun disablebutton() {
        for (i:Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun updatValue(row: Int, col: Int, player: Boolean) {
//
        val text:String =if(player) "X" else "0"
        val value:Int =if(player) 1 else 0

//        if(player) "X" else "0"
//        if(player) 1 else 0

        board[row][col].apply{
            isEnabled=false
            setText(text)

        }
        boardStatus[row][col]=value
    }
}