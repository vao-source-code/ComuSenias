package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.comusenias.domain.models.ResultOverlayView
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CameraViewModel @Inject constructor(
    private val useCases: CameraUseCases, @ApplicationContext private val context: Context
) : ViewModel() {

    private val _recognitionResults = MutableStateFlow<ResultOverlayView?>(null)
    val recognitionResults: StateFlow<ResultOverlayView?> = _recognitionResults


    fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            useCases.showCameraPreview(preview, lifecycleOwner)
        }
    }

    //Este codigo funciona
    // depues implementamos en jetpack compose en otra parte que es solamente usa la funcionalidad de sacar fotos
    fun captureAndSave(navController: NavController) {
        viewModelScope.launch {
            useCases.captureAndSave(navController)
        }
    }

    fun startObjectDetection() {
        viewModelScope.launch {
            val resultsFlow = useCases.startObjectDetection()
            resultsFlow.collect { results ->
                _recognitionResults.value = results
            }
        }
    }

    fun stopCameraPreview() {
        viewModelScope.launch {
            useCases.stopCameraPreview()
        }
    }


}