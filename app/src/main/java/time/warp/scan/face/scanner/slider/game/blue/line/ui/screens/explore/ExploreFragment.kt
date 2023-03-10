package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import time.warp.scan.face.scanner.slider.game.blue.line.R
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.openTrendingButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_explore_to_navigation_trending)
        }
    }

}