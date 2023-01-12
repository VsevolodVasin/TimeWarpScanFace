package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.on_save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentOnSaveBinding

class OnSaveFragment : Fragment() {

    lateinit var binding: FragmentOnSaveBinding

    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uri = it.getString("uri")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnSaveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            imageView15.setImageURI(uri!!.toUri())
            imageView11.setOnClickListener {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}