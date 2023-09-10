package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.use_cases.auth.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CameraViewModel @Inject constructor(private val useCases: CameraUseCases):ViewModel() {
    suspend fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner){
        useCases.invoke(preview,lifecycleOwner)
    }

    suspend fun captureAndSave(context: Context){
        useCases.invoke(context)
    }
}