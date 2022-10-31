package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeStoreViewModel: ViewModel() {
val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf<Shoe>())
val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    fun addShoe(shoe:Shoe?){
        shoe?.let{_shoeList.value?.add(it)}
    }
}