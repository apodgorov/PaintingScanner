package com.podgorovaa.paintingscanner

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.podgorovaa.paintingscanner.ScanBarcodeFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScanBarcodeViewModel : ViewModel() {

    val progressState: LiveData<Boolean> get() = _progressState
    private val _progressState = MutableLiveData<Boolean>()

    val navigation: LiveData<NavDirections?> get() = _navigation
    private val _navigation = MutableLiveData<NavDirections?>()

    init {
        _progressState.value = false
    }

    fun searchBarcode(barcode: String) {
        _progressState.value = true
        viewModelScope.launch {
            delay(1000)
            _navigation.value = ScanBarcodeFragmentDirections.scanDestinationToSuccessDestination(barcode)
            _progressState.value = false
        }
    }

    fun doneNavigating() {
        _navigation.value = null
    }
}