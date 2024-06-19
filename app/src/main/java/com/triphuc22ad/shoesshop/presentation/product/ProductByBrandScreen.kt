package com.triphuc22ad.shoesshop.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.components.ProductCard
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByBrandViewModel @Inject constructor(
    private val productService: ProductService,
    private val brandService: BrandService,
    private val appStateRepository: AppStateRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ProductByBrandState())
    val state: StateFlow<ProductByBrandState> = _state.asStateFlow()

    private val brandId: Int =
        savedStateHandle["brandId"] ?: 0


    fun fetchData() {
        viewModelScope.launch {
            val response = brandService.getBrandById(brandId);
            if (response.isSuccessful) {
                val brand = response.body()!!.data
                if (brand != null) {
                    val productResponse = productService.getAllProductsByBrandId(
                        brandId,
                        state.value.page,
                        state.value.size
                    );
                    if (productResponse.isSuccessful) {
                        val pagedResponse = productResponse.body()
                        if (pagedResponse != null) {
                            _state.value = _state.value.copy(
                                brandName = brand.name,
                                products = pagedResponse.content,
                                page = if (pagedResponse.page + 1 > pagedResponse.totalPages) pagedResponse.totalPages else pagedResponse.page,
                                totalPage = pagedResponse.totalPages
                            )
                        }
                    }
                }
            } else {
                appStateRepository.updateNotify("brand not found")
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

data class ProductByBrandState(
    val brandName: String = "",

    val products: List<Product> = emptyList(),

    val page: Int = 0,
    val size: Int = 10,
    val lastPage: Int = 0,
    val totalPage: Int = 0,
)

@Composable
fun ProductByBrandScreen(
    productByBrandViewModel: ProductByBrandViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
    navigateToProductDetail: (Int) -> Unit = {},
) {
    val state by productByBrandViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        productByBrandViewModel.fetchData()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TopTitleBar(
            name = "Shoes of brand '${state.brandName}'", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(state.products) {
                ProductCard(
                    product = it,
                    onClick = { navigateToProductDetail(it.id) }
                )
            }

            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { productByBrandViewModel.prevPage() },
                        enabled = state.page > 0,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Previous", fontSize = 12.sp)
                    }
                    Text("Page ${if (state.page != 0) state.page + 1 else 0} of ${state.totalPage}")
                    Button(
                        onClick = { productByBrandViewModel.nextPage() },
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