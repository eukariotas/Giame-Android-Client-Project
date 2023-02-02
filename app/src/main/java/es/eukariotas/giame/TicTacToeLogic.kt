package es.eukariotas.giame

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import es.eukariotas.giame.databinding.FragmentSecondBinding


class SecondFragment: Fragment(){

    var playerTurn = true

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


    var player1Count = 0
    var player2Count = 0


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
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1


    fun playnow(buttonSelected:Button , currCell:Int)
    { val audio = MediaPlayer.create(this , R.raw.poutch)
        if(activeUser == 1)
        {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)
            audio.start()
            buttonSelected.isEnabled = false
            Handler().postDelayed(Runnable { audio.release() } , 200)
            val checkWinner = checkwinner()
            if(checkWinner == 1){
                Handler().postDelayed(Runnable { reset() } , 2000)
            }
            else if(singleUser){
                Handler().postDelayed(Runnable {
                    robot() } , 500)
            }
            else
                activeUser = 2
        }
        else
        {
            buttonSelected.text = "O"
            audio.start()
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            activeUser = 1
            player2.add(currCell)
            emptyCells.add(currCell)
            Handler().postDelayed(Runnable { audio.release() } , 200)
            buttonSelected.isEnabled = false
            val checkWinner = checkwinner()
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
        Handler().postDelayed(Runnable {
            binding.button10.isEnabled = true
                                       } , 2200)
    }
}

