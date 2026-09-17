package com.example.islamicapp.taps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.AppContant
import com.example.islamicapp.ChaptersAdaptors

import com.example.islamicapp.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {
    lateinit var viewBinding : FragmentQuranBinding
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { viewBinding = FragmentQuranBinding.inflate(
        inflater,
        container,
        false
    )

        return viewBinding.root
    }

    val chapters = AppContant.getChapters()
    lateinit var adaptors: ChaptersAdaptors
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adaptors = ChaptersAdaptors(chapters)
        viewBinding.chapterRecycler.adapter = adaptors

    }
    
}