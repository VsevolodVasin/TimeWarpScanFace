package com.example.timewarpscan.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timewarpscan.databinding.PhotoAndVideoItemBinding
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


class TrendingAdapter(
    private val itemsList: MutableList<Pair<Int, String>>,
    private val onClickListener: OnClickListener,
): RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>(){

    inner class TrendingViewHolder(private val binding: PhotoAndVideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // first - unique id, second - url for preview
        fun bind(item: Pair<Int, String>, onClickListener: OnClickListener) {
            binding.apply {
                val transformation: Transformation = RoundedTransformationBuilder()
                    .cornerRadiusDp(16f)
                    .oval(false)
                    .build()
                Picasso.get()
                    .load(item.second)
                    .fit()
                    .transform(transformation)
                    .into(imageView)
                root.setOnClickListener { onClickListener.onClick(item.first) }
            }
        }

    }

    class OnClickListener(val clickListener: (animResId: Int) -> Unit) {
        fun onClick(animResId: Int) = clickListener(animResId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = PhotoAndVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size
}