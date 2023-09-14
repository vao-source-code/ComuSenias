package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CameraViewModel @Inject constructor(private val useCases: CameraUseCases):ViewModel() {
     fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner){
         viewModelScope.launch {
             useCases.showCameraPreview(preview, lifecycleOwner)
         }
    }
     fun captureAndSave(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            useCases.captureAndSave(context)
        }
    }
}