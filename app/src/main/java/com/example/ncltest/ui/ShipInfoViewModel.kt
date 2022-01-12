package com.example.ncltest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ncltest.models.ShipResponse
import com.example.ncltest.network.ship.ShipManager
import com.example.ncltest.util.state.AwaitResult
import com.example.ncltest.util.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipInfoViewModel @Inject constructor(private val shipManager: ShipManager): ViewModel() {
    private val _shipLiveData: MutableLiveData<State<ShipResponse>> = MutableLiveData()
    val shipLiveData: LiveData<State<ShipResponse>> = _shipLiveData

    fun fetchShipInfo(name: String) {
        _shipLiveData.value = State.Loading
        viewModelScope.launch {
            when (val result = shipManager.fetchShipInfo(name)) {
                is AwaitResult.Ok -> {
                    _shipLiveData.value = State.Success(result.value)
                }
                is AwaitResult.Error -> {
                    _shipLiveData.value = State.Error(result.exception)
                }
            }
        }
    }
}