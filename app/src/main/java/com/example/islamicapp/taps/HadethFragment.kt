package com.example.islamicapp.taps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.islamicapp.Hadeeth
import com.example.islamicapp.HadesPagerAdaptor
import com.example.islamicapp.databinding.FragmentHadethBinding

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

    private lateinit var adapters: HadesPagerAdaptor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapters = HadesPagerAdaptor(loadHadethList())

        binding.hadethPager.offscreenPageLimit = 3
        binding.hadethPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.hadethPager.adapter = adapters

        // ابدأ من المنتصف ليتحرك يميناً ويساراً (دوران لا نهائي)
        val middle = Int.MAX_VALUE / 2
        binding.hadethPager.setCurrentItem(middle, false)
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