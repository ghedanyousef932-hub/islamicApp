package com.example.islamicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.databinding.QuranItemBinding
import com.example.islamicapp.taps.SuraActivity

class ChaptersAdaptors(val chapters: List<Chapter>) : RecyclerView.Adapter<ChaptersAdaptors.ViewHolder>() {

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ViewHolder {
        val itemBinding = QuranItemBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        p0: ViewHolder,
        p1: Int
    ) {
        val chapter = chapters[p1]

        p0.itemBinding.souraNumber.text = (chapter.index + 1).toString()
        p0.itemBinding.souraEN.text = chapter.titleEn
        p0.itemBinding.souraVerses.text = chapter.ayahCount
        p0.itemBinding.souraAR.text = chapter.titleAr
        p0.itemView.setOnClickListener {
                val intent = Intent(it.context, SuraActivity::class.java)
                intent.putExtra(SuraActivity.EXTRA_CHAPTER_INDEX, chapter.index)

                it.context.startActivity(intent)
            }

    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    class ViewHolder(val itemBinding: QuranItemBinding) : RecyclerView.ViewHolder(itemBinding.root)
}
