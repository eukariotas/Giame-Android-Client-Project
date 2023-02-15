package es.eukariotas.giame

import android.app.AlertDialog
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.badlogic.gdx.Gdx.audio
import es.eukariotas.giame.databinding.FragmentSecondBinding
import kotlin.system.exitProcess


class SecondFragment: Fragment(){

    var playerTurn = true
    var player1Count = 0
    var player2Count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1
    var singlePlayer = true

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.button10.setOnClickListener {
            reset()

        }
    }




    fun clickfun(view:View)
    {
        if(playerTurn) {
            val but = view as Button
            var cellID = 0
            when (but.id) {
                binding.button.id -> cellID = 1
                binding.button2.id -> cellID = 2
                binding.button3.id -> cellID = 3
                binding.button4.id -> cellID = 4
                binding.button5.id -> cellID = 5
                binding.button6.id -> cellID = 6
                binding.button7.id -> cellID = 7
                binding.button8.id -> cellID = 8
                binding.button9.id -> cellID = 9
            }
            playerTurn = false;
            Handler().postDelayed(Runnable { playerTurn = true } , 600)
            playnow(but, cellID)
        }
    }


    fun playnow(buttonSelected:Button , currCell:Int)
    {
        if(activeUser == 1)
        {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)
            buttonSelected.isEnabled = false
            val checkWinner = checkWinner()
            if(checkWinner == 1){
                Handler().postDelayed(Runnable { reset() } , 2000)
            }
            else if(singlePlayer){
                Handler().postDelayed(Runnable {
                    robot() } , 500)
            }
            else
                activeUser = 2
        }
        else
        {
            buttonSelected.text = "O"
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            activeUser = 1
            player2.add(currCell)
            emptyCells.add(currCell)
            buttonSelected.isEnabled = false
            val checkWinner = checkWinner()
            if(checkWinner == 1)
                Handler().postDelayed(Runnable { reset() } , 4000)
        }
    }


    fun reset()
    {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1;
        for(i in 1..9)
        {
            var buttonselected : Button?
            buttonselected = when(i){
                1 -> binding.button
                2 ->  binding.button2
                3 ->  binding.button3
                4 ->  binding.button4
                5 ->  binding.button5
                6 ->  binding.button6
                7 ->  binding.button7
                8 ->  binding.button8
                9 ->  binding.button9
                else -> { binding.button}
            }
            buttonselected.isEnabled = true
            buttonselected.text = ""
            binding.textView.text = "Player1 : $player1Count"
            binding.textView2.text = "Player2 : $player2Count"
        }
    }


    fun disableReset()
    {
        binding.button10.isEnabled = false
        binding.button10.postDelayed(Runnable {
            binding.button10.isEnabled = true
                                       } , 2200)
    }
    fun checkWinner(): Int {
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)
            || player1.contains(1) && player1.contains(5) && player1.contains(9)
            || player1.contains(1) && player1.contains(4) && player1.contains(7)
            || player1.contains(2) && player1.contains(5) && player1.contains(8)
            || player1.contains(3) && player1.contains(6) && player1.contains(9)
            || player1.contains(3) && player1.contains(5) && player1.contains(7)
            || player1.contains(4) && player1.contains(5) && player1.contains(6)
            || player1.contains(7) && player1.contains(8) && player1.contains(9)
        ) {
            player1Count++
            buttonDisable()
            binding.textView.text = "Player1 : $player1Count"
            disableReset()
            val build = AlertDialog.Builder(context)
            build.setTitle("Game Over")
            build.setMessage("You have won the game.."+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) || (player2.contains(
                1
            ) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(
                7
            ) && player2.contains(8) && player2.contains(9)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) || (player2.contains(
                1
            ) && player2.contains(5) && player2.contains(9)) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) || (player2.contains(2) && player2.contains(
                5
            ) && player2.contains(8))
        ) {
            player2Count += 1
            buttonDisable()
            disableReset()
            val build = AlertDialog.Builder(context)
            build.setTitle("Game Over")
            build.setMessage("Opponent have won the game"+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()

            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
            return 1
        } else if (emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3) && emptyCells.contains(
                4
            ) && emptyCells.contains(5) && emptyCells.contains(6) && emptyCells.contains(7) &&
            emptyCells.contains(8) && emptyCells.contains(9)
        ) {

            val build = AlertDialog.Builder(context)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            build.show()
            return 1
        }
        return 0
    }
    fun robot() {
        val rnd = (1..9).random()
        if (emptyCells.contains(rnd))
            robot()
        else {
            val buttonselected: Button?
            buttonselected = when (rnd) {
                1 -> binding.button
                2 -> binding.button2
                3 -> binding.button3
                4 -> binding.button4
                5 -> binding.button5
                6 -> binding.button6
                7 -> binding.button7
                8 -> binding.button8
                9 -> binding.button9
                else -> {
                    binding.button
                }
            }
            emptyCells.add(rnd);
            buttonselected.text = "O"
            buttonselected.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(rnd)
            buttonselected.isEnabled = false
            var checkWinner = checkWinner()
            if (checkWinner == 1)
                Handler().postDelayed(Runnable { reset() }, 2000)
        }
    }
    fun buttonDisable() {
        for (i in 1..9) {
            val buttonSelected = when (i) {
                1 -> binding.button
                2 -> binding.button2
                3 -> binding.button3
                4 -> binding.button4
                5 -> binding.button5
                6 -> binding.button6
                7 -> binding.button7
                8 -> binding.button8
                9 -> binding.button9
                else -> {
                    binding.button
                }

            }
            if (buttonSelected.isEnabled == true)
                buttonSelected.isEnabled = false
        }
    }


}

