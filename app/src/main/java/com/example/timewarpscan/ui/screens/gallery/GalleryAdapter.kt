package com.example.timewarpscan.ui.screens.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timewarpscan.databinding.PhotoAndVideoItemBinding
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class GalleryAdapter(
    private val itemsList: MutableList<GalleryItem>,
    private val onClickListener: OnClickListener,
): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    inner class GalleryViewHolder(private val binding: PhotoAndVideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GalleryItem, onClickListener: OnClickListener) {
            binding.apply {
                if (item.type == GalleryItemType.IMG) {
                    playCircleImageView.visibility = View.GONE
                }
                val transformation: Transformation = RoundedTransformationBuilder()
                    .cornerRadiusDp(16f)
                    .oval(false)
                    .build()
                Picasso.get()
                    .load(item.uri)
                    .fit()
                    .transform(transformation)
                    .into(imageView)
                root.setOnClickListener { onClickListener.onClick(item.id) }
            }
        }

    }

    class OnClickListener(val clickListener: (animResId: Int) -> Unit) {
        fun onClick(animResId: Int) = clickListener(animResId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = PhotoAndVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size
}