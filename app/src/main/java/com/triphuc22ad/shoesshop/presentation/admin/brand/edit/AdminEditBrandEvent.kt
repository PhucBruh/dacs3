package com.triphuc22ad.shoesshop.presentation.admin.brand.edit

sealed class AdminEditBrandEvent {
    data class ChangeName(val name: String) : AdminEditBrandEvent()
    data class ChangeImage(val image: String) : AdminEditBrandEvent()

    data object DeleteCheckImg : AdminEditBrandEvent()
    data object CheckImg : AdminEditBrandEvent()
}