package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraFragmentViewModel : ViewModel() {
    val recordMode = MutableLiveData(false)
    val effectMode = MutableLiveData(false)
}