package com.example.islamicapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.databinding.AhadesBinding

class HadesPagerAdaptor(
    private val hadeethList: List<Hadeeth>
) : RecyclerView.Adapter<HadesPagerAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = AhadesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val realPosition = position % hadeethList.size

        holder.binding.title.text = hadeethList[realPosition].title
        holder.binding.hades.text = hadeethList[realPosition].content
    }

    override fun getItemCount(): Int {
        return if (hadeethList.isEmpty()) 0 else Int.MAX_VALUE
    }

    class ViewHolder(
        val binding: AhadesBinding
    ) : RecyclerView.ViewHolder(binding.root)
}