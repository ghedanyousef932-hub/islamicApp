package com.example.islamicapp.taps

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.Hadeeth
import com.example.islamicapp.HadesAdaptor
import com.example.islamicapp.MainActivity
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentHadethBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy

class HadethFragment : Fragment() {

    lateinit var binding: FragmentHadethBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadethBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }

     lateinit var adapters: HadesAdaptor
         var layoutManager = CarouselLayoutManager()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapters = HadesAdaptor(loadHadethList())
    layoutManager = CarouselLayoutManager(

        HeroCarouselStrategy(),
                CarouselLayoutManager.HORIZONTAL,
    )
        layoutManager.carouselAlignment =CarouselLayoutManager.ALIGNMENT_CENTER

        binding.hadethRecycler.adapter = adapters

        binding.hadethRecycler.layoutManager = layoutManager

        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.hadethRecycler)
        binding.backArrow.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.quran
        }
        binding.hadethRecycler.addItemDecoration(
            MarginItemDecoration(
                6
            )
        )
    }


    private fun loadHadethList(): List<Hadeeth> {

        val list = mutableListOf<Hadeeth>()

        for (i in 1..50) {

            val hadeth = loadHadeeth(i)

            list.add(Hadeeth(hadeth.first, hadeth.second))
        }

        return list
    }


    private fun loadHadeeth(fileNu: Int): Pair<String, String> {

        return try {

            val text = requireContext().assets.open("Hadeeth/h$fileNu.txt")
                .bufferedReader(Charsets.UTF_8)
                .use { it.readText() }
                .trim()

            val lines = text.lines()

            val titleIndex = lines.indexOfFirst { line ->
                line.trim().isNotEmpty() &&
                        line.any { char ->
                            char in '\u0600'..'\u06FF'
                        }
            }

            if (titleIndex == -1) {
                return Pair("", text)
            }

            val title = lines[titleIndex].trim()

            val content = lines
                .drop(titleIndex + 1)
                .joinToString("\n")
                .trim()

            Pair(title, content)

        } catch (e: Exception) {
            Pair("", "")
        }
    }

}