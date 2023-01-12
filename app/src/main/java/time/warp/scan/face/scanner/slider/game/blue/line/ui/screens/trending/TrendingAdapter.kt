package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.PhotoAndVideoItemBinding


class TrendingAdapter(
    private val itemsList: MutableList<TrendingItem>,
    private val onClickListener: OnClickListener,
): RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>(){

    inner class TrendingViewHolder(private val binding: PhotoAndVideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingItem, onClickListener: OnClickListener) {
            binding.apply {
                val transformation: Transformation = RoundedTransformationBuilder()
                    .cornerRadiusDp(16f)
                    .oval(false)
                    .build()
                Picasso.get()
                    .load(item.previewResId)
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