package com.example.comusenias.presentation.view_model

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.ResultOverlayView
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CameraViewModel @Inject constructor(private val useCases: CameraUseCases) : ViewModel() {

    private val _recognitionResults = MutableStateFlow<ResultOverlayView?>(null)
    val recognitionResults: StateFlow<ResultOverlayView?> = _recognitionResults

    var response by mutableStateOf<Boolean>(false)
        private set

    fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            useCases.showCameraPreview(preview, lifecycleOwner)
        }
    }

    //Este codigo funciona
    // depues implementamos en jetpack compose en otra parte que es solamente usa la funcionalidad de sacar fotos
    fun captureAndSave(context: Context) = viewModelScope.launch {
        response = false
        useCases.captureAndSave(context)
        response = true
    }


    fun sendImageToServer() = viewModelScope.launch {
        useCases.sendImageToServer(imageUri = Uri.EMPTY)
    }

    fun startObjectDetection() {
        viewModelScope.launch {
            val resultsFlow = useCases.startObjectDetection()
            resultsFlow.collect { results ->
                _recognitionResults.value = results
            }
        }
    }


}