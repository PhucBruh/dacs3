package com.triphuc22ad.shoesshop.presentation.special_offers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.presentation.home.components.SpecialOfferBanner
import com.triphuc22ad.shoesshop.ui.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecialOffersViewModel @Inject constructor(
    private val specialOfferService: SpecialOfferService,
) : ViewModel() {
    private val _state = MutableStateFlow(SpecialOfferListUiState())
    val state: StateFlow<SpecialOfferListUiState> = _state.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val specialOffersResponse =
                specialOfferService.getAllSpecialOffer(state.value.page, state.value.size);
            if (specialOffersResponse.isSuccessful) {
                val pagedResponse = specialOffersResponse.body()
                val specialOffers = pagedResponse?.content
                if (specialOffers != null) {
                    _state.value = _state.value.copy(
                        specialOffers = specialOffers,
                    )
                }
            }
        }
    }

    fun nextPage() {
        if (state.value.page + 1 < state.value.totalPage) {
            _state.value = _state.value.copy(page = _state.value.page + 1)
            fetchData()
        }
    }

    fun prevPage() {
        if (state.value.page > 0) {
            _state.value = _state.value.copy(page = _state.value.page - 1)
            fetchData()
        }
        fetchData()
    }
}


data class SpecialOfferListUiState(
    val specialOffers: List<SpecialOffer> = emptyList(),

    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,
)

@Composable
fun SpecialOffersScreen(
    navigateBack: () -> Unit = {},
    navigateToProductDetail: (Int) -> Unit = {},
    viewModel: SpecialOffersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
    ) {
        TopTitleBar(name = "Special Offers", onLeftAction = navigateBack)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(state.specialOffers) {
                SpecialOfferBanner(
                    specialOffer = it,
                    onClick = { navigateToProductDetail(it.productId) }
                )

            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { viewModel.prevPage() },
                        enabled = state.page > 0,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Previous", fontSize = 12.sp)
                    }

                    Text("Page ${if (state.page != 0) state.page + 1 else 0} of ${state.totalPage}")

                    Button(
                        onClick = { viewModel.nextPage() },
                        enabled = state.page + 1 < state.totalPage,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Next", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SpecialOffersScreenPreview() {
    AppTheme {
        Surface {
            SpecialOffersScreen()
        }
    }
}
