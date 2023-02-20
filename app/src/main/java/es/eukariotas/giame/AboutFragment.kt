package es.eukariotas.giame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.eukariotas.giame.databinding.FragmentAboutBinding
import es.eukariotas.giame.databinding.FragmentInfoBinding

class AboutFragment : Fragment() {
   private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
       }


}