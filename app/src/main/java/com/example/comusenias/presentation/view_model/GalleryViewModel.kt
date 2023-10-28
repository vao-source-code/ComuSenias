package com.example.comusenias.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.vowels.VowelsResponse
import com.example.comusenias.domain.use_cases.gallery.GalleryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val useCases: GalleryUseCases) : ViewModel() {

    private val _vowelsResults = MutableStateFlow<VowelsResponse?>(null)
    val vowelsResults: StateFlow<VowelsResponse?> = _vowelsResults
    fun postGallery(fileMultipartBody: MultipartBody.Part) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCases.showVowels(fileMultipartBody = fileMultipartBody)
            _vowelsResults.value = VowelsResponse(response.letra, response.image_base64)
        }
    }
}