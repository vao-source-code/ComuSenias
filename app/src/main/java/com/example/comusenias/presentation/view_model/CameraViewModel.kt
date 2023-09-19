package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.ObjectDetectionResult
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CameraViewModel @Inject constructor(private val useCases: CameraUseCases):ViewModel() {

    private val _objectDetectionResults = MutableStateFlow<List<ObjectDetectionResult>>(emptyList())
    val objectDetectionResults: StateFlow<List<ObjectDetectionResult>> = _objectDetectionResults

    fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner){
         viewModelScope.launch {
             useCases.showCameraPreview(preview, lifecycleOwner)
         }
    }
    //Funciona el boton para guardar fotos, depues implementamos en jetpack compose en otra parte que es solamente usa la funcionalidad de sacar fotos
     fun captureAndSave(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            useCases.captureAndSave(context)
        }
    }

    fun startObjectDetection(){
        viewModelScope.launch (Dispatchers.IO){
            val resultsFlow = useCases.startObjectDetection()
            resultsFlow.collect { results ->
                _objectDetectionResults.value = results
            }
        }
    }


}