package com.triphuc22ad.shoesshop.presentation.admin.product.edit

sealed class EditProductEvent {
    data class ChangeName(val value: String) : EditProductEvent()
    data class ChangeDescription(val value: String) : EditProductEvent()
    data class ChangePrice(val value: Double) : EditProductEvent()
    data class ChangeMainImg(val value: String) : EditProductEvent()
    data class ChangeBrandId(val value: Int) : EditProductEvent()

    data class AddEditSize(val value: Int) : EditProductEvent()
    data class DeleteEditSize(val value: Int) : EditProductEvent()
    data class AddEditColor(val name: String, val value: String) : EditProductEvent()
    data class DeleteEditColor(val name: String, val value: String) : EditProductEvent()
    data class AddEditImg(val value: String) : EditProductEvent()
    data class DeleteEditImg(val value: String) : EditProductEvent()
    data object CheckImg : EditProductEvent()
    data object DeleteCheckImg : EditProductEvent()

    data class DeleteColor(val id: Int) : EditProductEvent()
    data class DeleteSize(val id: Int) : EditProductEvent()
}