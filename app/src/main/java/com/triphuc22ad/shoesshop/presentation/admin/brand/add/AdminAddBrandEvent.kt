package com.triphuc22ad.shoesshop.presentation.admin.brand.add

sealed class AdminAddBrandEvent {

    data class ChangeName(val name: String) : AdminAddBrandEvent()
    data class ChangeImage(val image: String) : AdminAddBrandEvent()
    data object CheckImg : AdminAddBrandEvent()
    data object DeleteCheckImg : AdminAddBrandEvent()
}