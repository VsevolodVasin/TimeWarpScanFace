package com.example.timewarpscan.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timewarpscan.R
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    lateinit var binding: FragmentGalleryBinding
    private val spanCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val galleryItemsList: MutableList<GalleryItem> = ArrayList()
        galleryItemsList.add(
            GalleryItem(
                0,
                GalleryItemType.IMG,
                //TODO ВЕЗДЕ ВРЕМЕННО URI ЭТО URL НА КАРТИНКИ С ИНТЕРНЕТА
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                1,
                GalleryItemType.IMG,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                2,
                GalleryItemType.VIDEO,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                3,
                GalleryItemType.IMG,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                4,
                GalleryItemType.IMG,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                5,
                GalleryItemType.VIDEO,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                6,
                GalleryItemType.IMG,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                7,
                GalleryItemType.IMG,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        galleryItemsList.add(
            GalleryItem(
                8,
                GalleryItemType.VIDEO,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg"
            )
        )

        val galleryLayoutManager = GridLayoutManager(activity, spanCount)
        val galleryAdapter = GalleryAdapter(galleryItemsList, galleryItemListener)

        binding.apply {
            galleryRecyclerView.layoutManager = galleryLayoutManager
            galleryRecyclerView.adapter = galleryAdapter
            settingsButton.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_gallery_to_settingsFragment)
                (requireActivity() as NavigationHelper).hideBottomAppBar()
            }
        }
    }

    private val galleryItemListener = GalleryAdapter.OnClickListener {
        Log.i("TAG", "You click on $it")
    }

}