package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.game

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    lateinit var binding: FragmentHelpBinding
    private val ARG_LEVEL_VIDEO_URI_PATH = "videoUriPath"
    private var videoUriPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoUriPath = it.getString(ARG_LEVEL_VIDEO_URI_PATH)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHelpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            videoView.setVideoURI(Uri.parse(videoUriPath))
            videoView.start()
            helpBackButton.setOnClickListener { findNavController().popBackStack() }
            helpPlayButton.setOnClickListener { findNavController().popBackStack() }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}