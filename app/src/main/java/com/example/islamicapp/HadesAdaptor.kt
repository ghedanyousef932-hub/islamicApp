package com.example.islamicapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.databinding.AhadesBinding

class HadesAdaptor(val hadeethList: List<Hadeeth>) : RecyclerView.Adapter<HadesAdaptor.ViewHolder>() {

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
        val hadeeth = hadeethList[position]

        holder.binding.title.text = hadeeth.title
        holder.binding.hades.text = hadeeth.content
    }

    override fun getItemCount(): Int {
        return hadeethList.size
    }

    class ViewHolder(val binding: AhadesBinding) : RecyclerView.ViewHolder(binding.root)
}
