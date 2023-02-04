package es.eukariotas.giame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.FragmentFirstBinding
import es.eukariotas.giame.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
   }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val ajedrezFragment = AjedrezFragment()
            if (view != null) {
                fragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.includefragmentGame, ajedrezFragment)
                fragmentTransaction.commit()
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }


}