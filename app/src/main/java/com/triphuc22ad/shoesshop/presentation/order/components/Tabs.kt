import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import com.triphuc22ad.shoesshop.presentation.order.components.ActiveScreen
import com.triphuc22ad.shoesshop.presentation.order.components.CompletedScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabScreen(
    inCompletedOrders: List<OrderInfo>,
    completedOrders: List<OrderInfo>,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 2 })

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                    text = { Text(text = "Active") }
                )
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                    text = { Text(text = "Completed") }
                )
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> ActiveScreen(inCompletedOrders)
                    1 -> CompletedScreen(completedOrders)
                }
            }
        }
    }
}

@Preview
@Composable
fun TabScreenPreview() {
    TabScreen(List(4) {
        OrderInfo(
            id = it,
            description = "giao vao buoi chieu",
            shippingAddress = "da nang",
            price = 100000.0,
            status = "PENDING"
        )
    }, List(4) {
        OrderInfo(
            id = it,
            description = "giao vao buoi chieu",
            shippingAddress = "da nang",
            price = 100000.0,
            status = "COMPLETED"
        )
    })
}