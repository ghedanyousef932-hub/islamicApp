package com.example.islamicapp.taps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.islamicapp.AppContant

import com.example.islamicapp.databinding.SouraBinding

class SuraActivity : AppCompatActivity() {

    private lateinit var binding: SouraBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = SouraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chapterIndex = intent.getIntExtra(EXTRA_CHAPTER_INDEX, 0)
        val title = "سورة " + AppContant.arabicNames[chapterIndex]

        binding.souraTitle.text = title
        binding.textedSoura.text = loadSouraText(chapterIndex + 1)

    }

    private fun loadSouraText(fileNumber: Int): String {
        return try {
            assets.open("Suras/$fileNumber.txt")
                .bufferedReader(Charsets.UTF_8)
                .use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }

    companion object {
        const val EXTRA_CHAPTER_INDEX = "chapter_index"
    }
}