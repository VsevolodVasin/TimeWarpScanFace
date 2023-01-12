package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import time.warp.scan.face.scanner.slider.game.blue.line.R
import time.warp.scan.face.scanner.slider.game.blue.line.core.helpers.NavigationHelper
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.playGameButton.setOnClickListener {
            (requireActivity() as NavigationHelper).hideBottomAppBar()
            findNavController().navigate(R.id.action_navigation_game_to_levelsFragment)
        }
    }

}