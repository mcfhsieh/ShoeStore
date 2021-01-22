package com.udacity.shoestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel(){

    var shoeList = mutableListOf<Shoe>()
    private val _listOfShoes = MutableLiveData<List<Shoe>>()
    val listOfShoes: MutableLiveData<List<Shoe>>
        get() = _listOfShoes
    private val _shoeCreated = MutableLiveData<Boolean>()
    val shoeCreated:MutableLiveData<Boolean>
        get() = _shoeCreated


    var shoeSize:String = ""



    fun updateShoes(newShoe: Shoe){
        newShoe.size = shoeSize.toDouble()
        shoeList.add(newShoe)
        _listOfShoes.value = shoeList
        shoeCreated.value = true
        shoeSize = ""
    }

/*    fun getNewShoe(): Shoe{
        var name = shoeName
        var company = shoeCompany
        var size = shoeSize.toDouble()
        var description = shoeDescription
        var shoe = Shoe(name, size, company, description)
        return shoe
    }*/
}
