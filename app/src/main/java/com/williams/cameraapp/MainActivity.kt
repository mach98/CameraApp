package com.williams.cameraapp

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.core.content.ContextCompat
import com.williams.cameraapp.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import androidx.core.content.PermissionChecker
import java.util.concurrent.Executors

typealias LumaListener = (luma:Double) -> Unit
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var imageCapture:ImageCapture? = null

    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    private lateinit var cameraExecutor: ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       if(allPermissionsGranted()) {
           startCamera()
       }else{
           requestPermissions()
       }

        binding.imageCaptureButton.setOnClickListener{takePhoto()}
        binding.imageCaptureButton.setOnClickListener{captureVideo()}

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun captureVideo() {
        TODO("Not yet implemented")
    }

    private fun takePhoto() {
        TODO("Not yet implemented")
    }

    private fun requestPermissions(){}

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all{
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraApp"
        private const val FILENAME_FORMAT = "yyyy-mm-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply{
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}