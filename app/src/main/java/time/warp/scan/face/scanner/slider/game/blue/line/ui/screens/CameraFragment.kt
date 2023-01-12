package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.otaliastudios.cameraview.*
import com.otaliastudios.cameraview.controls.Audio
import com.otaliastudios.cameraview.controls.Engine
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.filters.BrightnessFilter
import com.otaliastudios.cameraview.frame.FrameProcessor
import kotlinx.coroutines.*
import time.warp.scan.face.scanner.slider.game.blue.line.R
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentCameraBinding
import java.io.File
import java.io.FileOutputStream


open class CameraFragment : Fragment() {



    private var effectMode = false

    lateinit var overlayEffect : ImageView
    lateinit var overlayLine : ImageView

    lateinit var camera : CameraView
    private var effectRunning = true
    private var timer = 3
    // The permissions we need for the app to work properly
    private val permissions = mutableListOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    var warpDirection = WARP_DIRECTION.VERTICAL

    var lastTime = 0L
    var mFacing = 0
    var frameRate = 5
    var lineCount = 0
    var lineResolution = 1
    var resolutionX = 480
    var resolutionY = 640

    var resultBitmap: Bitmap? = null
    var subBitmap: Bitmap? = null


    enum class WARP_DIRECTION {
        VERTICAL, HORIZONTAL
    }

    enum class CAPTURE_MODE {
        PHOTO, VIDEO
    }


    private val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (permissions.all { it.value }) {
            initCamera()
        } else {
            view?.let { v ->
                Snackbar.make(v, "Camera not working without permissions", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") { ActivityCompat.finishAffinity(requireActivity()) }
                    .show()
            }
        }
    }

    lateinit var challengeName: String
    lateinit var challengeDescription: String
    var challengeImageId: Int = 0
    lateinit var binding : FragmentCameraBinding
    val viewModel : CameraFragmentViewModel by viewModels()
    val args: CameraFragmentArgs by navArgs()


    val frameProcessor = FrameProcessor {
       camera.takePictureSnapshot()
    }


    suspend fun effect(bitmap : Bitmap){
        resolutionX=bitmap.width
        resolutionY=bitmap.height

        if ((lineCount >= resolutionY || warpDirection != WARP_DIRECTION.VERTICAL) && (lineCount >= resolutionX || warpDirection != WARP_DIRECTION.HORIZONTAL || mFacing != 0) && (lineCount >= resolutionX || warpDirection != WARP_DIRECTION.HORIZONTAL || mFacing != 1)) {
            if (effectRunning) {
                effectRunning=false
            }
        } else if (effectRunning) {
            val currentTimeMillis = System.currentTimeMillis()
            Log.i("test", (currentTimeMillis - lastTime).toString())
            if (currentTimeMillis - lastTime >= frameRate) {
                lastTime = currentTimeMillis
                if (resultBitmap == null) {
                    withContext(Dispatchers.Main) {
                        initializeImageView()
                    }
                }
                if (warpDirection == WARP_DIRECTION.VERTICAL) {
                    subBitmap = if(lineCount+lineResolution>resolutionY){
                        Bitmap.createBitmap(
                            bitmap, 0, lineCount,
                            resolutionX, resolutionY-lineCount
                        )
                    } else {
                        Bitmap.createBitmap(
                            bitmap, 0, lineCount,
                            resolutionX, lineResolution
                        )
                    }
                } else if (warpDirection == WARP_DIRECTION.HORIZONTAL) {
                    subBitmap = if(lineCount+lineResolution>resolutionX){
                        Bitmap.createBitmap(
                            bitmap, lineCount, 0,
                            resolutionX-lineCount, resolutionY
                        )
                    } else {
                        Bitmap.createBitmap(
                            bitmap, lineCount, 0,
                            lineResolution, resolutionY
                        )
                    }
                }

                resultBitmap = overlay(
                    resultBitmap,
                    subBitmap, lineCount, warpDirection
                )

                val scan = drawScanEffect(bitmap, warpDirection, lineCount)

                withContext(Dispatchers.Main) {
                    binding.overlayEffect.setImageBitmap(resultBitmap)
                    binding.overlayLine.setImageBitmap(scan)
                }

                lineCount += lineResolution

            }
        }
    }

    fun initializeImageView() {
        val createBitmap = Bitmap.createBitmap(resolutionX, resolutionY, Bitmap.Config.ARGB_8888)
        resultBitmap = createBitmap
        createBitmap.eraseColor(0)
        binding.overlayEffect.setImageBitmap(resultBitmap)
    }

    private fun drawScanEffect(referenceBitmap: Bitmap, warp_direction: WARP_DIRECTION, i: Int) : Bitmap{
        val bitmap =  Bitmap.createBitmap(referenceBitmap.width, referenceBitmap.height, referenceBitmap.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.strokeWidth = 10.0f
        paint.color = ContextCompat.getColor(requireContext(), R.color.teal_200)
        if (warp_direction == WARP_DIRECTION.VERTICAL) {
            val f = (i + 5).toFloat()
            canvas.drawLine(0.0f, f, bitmap.width.toFloat(), f, paint)
        } else if (warp_direction == WARP_DIRECTION.HORIZONTAL) {
            val f2 = (i + 5).toFloat()
            canvas.drawLine(f2, 0.0f, f2, bitmap.height.toFloat(), paint)
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null as Paint?)
        return bitmap
    }


    fun overlay(bitmap: Bitmap?, bitmap2: Bitmap?, i: Int, warp_direction: WARP_DIRECTION): Bitmap {
        Matrix().preScale(1.0f, -1.0f)
        val createBitmap = Bitmap.createBitmap(bitmap!!.width, bitmap.height, bitmap.config)
        val canvas = Canvas(createBitmap)
        val paint: Paint? = null
        canvas.drawBitmap(bitmap, Matrix(), paint)
        if (warp_direction == WARP_DIRECTION.VERTICAL) {
            canvas.drawBitmap(bitmap2!!, 0.0f, i.toFloat(), paint)
        }
        if (warp_direction == WARP_DIRECTION.HORIZONTAL) {
            canvas.drawBitmap(bitmap2!!, i.toFloat(), 0.0f, paint)
        }
        return createBitmap
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = requireActivity()
        challengeImageId = args.challengeImage
        challengeDescription = args.challengeDescription
        challengeName = args.challengeName
        effectMode = args.challengeOrientation

        val dialogMusic = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        dialogMusic.setContentView(R.layout.song_overlay)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        with(binding){

            brightButton.setOnClickListener {
                brightCard.visibility = View.VISIBLE
                mainCard.visibility = View.GONE
                backLayout.setOnClickListener {
                    brightCard.visibility = View.GONE
                    mainCard.visibility = View.VISIBLE
                }
            }

            speedButton.setOnClickListener {
                speedCard.visibility = View.VISIBLE
                mainCard.visibility = View.GONE
                backLayout.setOnClickListener {
                    speedCard.visibility = View.GONE
                    mainCard.visibility = View.VISIBLE
                }
            }

            timerButton.setOnClickListener {
                timerCard.visibility = View.VISIBLE
                mainCard.visibility = View.GONE
                backLayout.setOnClickListener {
                    timerCard.visibility = View.GONE
                    mainCard.visibility = View.VISIBLE
                }
            }

            threeSecondsButton.setOnClickListener { timer = 3  }

            fiveSecondsButton.setOnClickListener {  timer = 5 }

            tenSecondsButton.setOnClickListener { timer = 10 }


            brightBar.setOnSeekBarChangeListener (object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    val filter =  BrightnessFilter()
                    val converted : Float = (((progress.toFloat() - 0) * 1) / 200) + 1
                    filter.brightness = converted
                    cameraView.filter = filter
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })


            musicButton.setOnClickListener {
                dialogMusic.show()
            }
            effectMode.setOnCheckedChangeListener { _, id ->
                when (id){
                    R.id.vertical->{
                        viewModel!!.effectMode.value = true
                        warpDirection = WARP_DIRECTION.VERTICAL
                    }
                    R.id.horizontal->{
                        viewModel!!.effectMode.value = false
                        warpDirection = WARP_DIRECTION.HORIZONTAL
                    }
                }
            }


            shot.setOnClickListener {
                 val bitmap = getScreenShotFromView(cameraView)
                 binding.overlayEffect.setImageBitmap(bitmap)

                mainCard.visibility  = View.GONE
                overlayShot.visibility = View.GONE
                timerOverlay.visibility = View.VISIBLE
                timer++
                timerTextView.text = "$timer"

                val timer = object: CountDownTimer((timer*1000).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val timerText = millisUntilFinished/1000
                        timerTextView.text = "$timerText"
                    }

                    override fun onFinish() {
                        timerOverlay.visibility = View.GONE

                        if(viewModel!!.recordMode.value!!){
                            cameraView.mode = Mode.VIDEO

                            val file = File("${requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)}/${System.currentTimeMillis()}.mp4")
                            file.parentFile?.mkdirs()
                            camera.takeVideoSnapshot(file, timer*1000)

                        } else {
                            cameraView.mode = Mode.PICTURE
                        }
                        camera.addFrameProcessor(frameProcessor)
                    }
                }

                CoroutineScope(Dispatchers.IO).launch {
                    runBlocking {
                        while(frameRate<150){
                            frameRate = if(this@CameraFragment.effectMode){
                                (viewModel!!.speed.value!! * 1000)  / (camera.height/lineResolution)
                            } else {
                                (viewModel!!.speed.value!! * 1000) / (camera.width/lineResolution)
                            }
                            if(frameRate<=150){
                                lineResolution+=1
                            }
                        }
                    }
                    timer.start()
                }



            }

            changeMode.setOnClickListener {
                viewModel!!.recordMode.value=!(viewModel!!.recordMode.value)!!
            }

            changeCameraButton.setOnClickListener {
                if (!camera.isTakingPicture && !camera.isTakingVideo) {
                    camera.toggleFacing()
                }
            }

            camera = cameraView
            this@CameraFragment.overlayEffect = overlayEffect
            this@CameraFragment.overlayLine = overlayLine
        }
        viewModel.effectMode.observe(requireActivity(), Observer {
            effectMode=it
        })


        return binding.root
    }

    protected fun allPermissionsGranted() = permissions.all {
        ActivityCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ActivityCompat.requestPermissions(requireActivity(),
             mutableListOf(Manifest.permission.RECORD_AUDIO).toTypedArray(),
            111)
        if (allPermissionsGranted()) {
            initCamera()
        } else {
            permissionRequest.launch(permissions.toTypedArray())
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }


    private fun getScreenShotFromView(v: View): Bitmap? {
        // create a bitmap object
        var screenshot: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        // return the bitmap
        return screenshot
    }



    private fun initCamera()  {

        camera.audio = Audio.OFF
        camera.setLifecycleOwner(this)
        camera.engine = Engine.CAMERA1
        camera.setExperimental(false)


        camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                result.toBitmap(BitmapCallback {
                    if(effectRunning){
                        CoroutineScope(Dispatchers.Default).launch {
                            effect(it!!)
                        }
                    } else{
                        binding.mainCard.visibility = View.VISIBLE
                        camera.removeFrameProcessor(frameProcessor)
                        binding.overlayShot.visibility = view!!.visibility
                        effectRunning = true

                        val file = File("${requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)}/${System.currentTimeMillis()}.jpg")
                        file.parentFile?.mkdirs()

                        val fOut = FileOutputStream(file)
                        val bmp = getScreenShotFromView(binding.cameraView)

                        bmp!!.compress(Bitmap.CompressFormat.JPEG,
                            100,
                            fOut)

                        fOut.flush()
                        fOut.close()

                        val bundle = Bundle()
                        bundle.putString("uri", file.toURI().toString())

                        findNavController().navigate(R.id.action_cameraFragment_to_onSaveFragment, bundle)

                    }

                })

            }

            override fun onVideoTaken(result: VideoResult) {
                super.onVideoTaken(result)

                binding.mainCard.visibility = View.VISIBLE
                camera.removeFrameProcessor(frameProcessor)
                binding.overlayShot.visibility = view!!.visibility
                effectRunning = true


            }

        })


    }





}