package com.triphuc22ad.shoesshop.presentation.admin.product.add

sealed class AddProductEvent {

    data class ChangeName(val value: String) : AddProductEvent()
    data class ChangeDescription(val value: String) : AddProductEvent()
    data class ChangePrice(val value: Double) : AddProductEvent()
    data class ChangeMainImg(val value: String) : AddProductEvent()
    data class ChangeBrandId(val value: Int) : AddProductEvent()
    data class AddSize(val value: Int) : AddProductEvent()
    data class DeleteSize(val value: Int) : AddProductEvent()
    data class AddColor(val name: String, val value: String) : AddProductEvent()
    data class DeleteColor(val name: String, val value: String) : AddProductEvent()
    data class AddImg(val value: String) : AddProductEvent()
    data class DeleteImg(val value: String) : AddProductEvent()
    data object CheckImg : AddProductEvent()
    data object DeleteCheckImg : AddProductEvent()
}