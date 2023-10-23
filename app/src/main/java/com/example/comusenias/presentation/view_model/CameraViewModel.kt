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
import com.example.comusenias.core.di.SPManager
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.ResultOverlayView
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
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

    var response by mutableStateOf<Response<Boolean>?>(null)
        private set

    private lateinit var spManager: SPManager

    init {
        spManager = SPManager(context = context)
    }

    fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            useCases.showCameraPreview(preview, lifecycleOwner)
        }
    }

    //Este codigo funciona
    // depues implementamos en jetpack compose en otra parte que es solamente usa la funcionalidad de sacar fotos
    fun captureAndSave(context: Context) = viewModelScope.launch(IO) {
        response = Response.Loading
        useCases.captureAndSave(context)
        response = Response.Success(true)
    }


    fun sendImageToServer() = viewModelScope.launch(IO) {
        val imageUri = Uri.parse(spManager.getString("imageUri", EMPTY_STRING))
        // useCases.sendImageToServer(imageUri = imageUri)
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