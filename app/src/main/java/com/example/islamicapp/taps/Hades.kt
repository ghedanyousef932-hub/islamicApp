
package com.example.islamicapp.taps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islamicapp.databinding.AhadesBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: AhadesBinding

class Hades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AhadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var allTitles = ""
        var allHadeeth = ""

        for (i in 1 .. 50) {

            val hadeeth = loadHadeeth(i)

            allTitles += "${hadeeth.first}\n\n"

            allHadeeth += "${hadeeth.second}\n\n"
            allHadeeth += "────────────────────\n\n"
        }

        binding.title.text = allTitles
        binding.hades.text = allHadeeth
    }

    private fun loadHadeeth(fileNu: Int): Pair<String, String> {

        return try {

            val text = assets.open("Hadeeth/h$fileNu.txt")
                .bufferedReader(Charsets.UTF_8)
                .use { it.readText() }
                .trim()

            val lines = text.lines()

            // البحث عن أول سطر عربي = العنوان
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

