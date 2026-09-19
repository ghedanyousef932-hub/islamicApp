package com.example.islamicapp.taps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentTsbehBinding

class TasbehFragment : Fragment() {

    private var _binding: FragmentTsbehBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val tasbeh: List<String> = listOf(
        "الحمد لله",
        "الله اكبر",
        "لا الله الا الله ",
        "سبحان الله"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTsbehBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text1.setOnClickListener {
            binding.text1.text = tasbeh.random()
        }

        binding.reset.setOnClickListener {
            binding.textNum.text = getString(R.string.counter_template, 0)
        }

        binding.imageButton.setOnClickListener {
            val numStr = binding.textNum.text.toString()
            val num = numStr.toIntOrNull() ?: 0
            binding.textNum.text = getString(R.string.counter_template, num + 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
