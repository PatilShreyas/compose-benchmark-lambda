package com.example.stockexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockexample.ui.component.Change
import com.example.stockexample.ui.component.HoldingDetail
import com.example.stockexample.ui.component.HoldingDetailData
import com.example.stockexample.ui.component.InvestmentSummary
import com.example.stockexample.ui.component.MarketIndice
import com.example.stockexample.ui.component.MarketIndiceData
import com.example.stockexample.ui.component.ReturnsSummary
import com.example.stockexample.ui.theme.LightGrey
import com.example.stockexample.ui.theme.StockExampleTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockExampleTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var indices by remember { mutableStateOf(emptyList<MarketIndiceData>().toImmutableList()) }
    var investmentSummary by remember {
        mutableStateOf(
            InvestmentSummary(
                "",
                "",
                ReturnsSummary("", 0f, Change.NEUTRAL)
            )
        )
    }
    var holdings by remember { mutableStateOf(emptyList<HoldingDetailData>().toImmutableList()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Stocks") },
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        MainScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            indices = indices,
            investmentSummary = investmentSummary,
            holdings = holdings
        )
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            FakeDataProducer.produce()
                .collect { (latestMarketIndices, latestInvestmentSummary, latestHoldings) ->
                    indices = latestMarketIndices
                    investmentSummary = latestInvestmentSummary
                    holdings = latestHoldings
                }
        }
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier,
    indices: ImmutableList<MarketIndiceData>,
    investmentSummary: InvestmentSummary,
    holdings: ImmutableList<HoldingDetailData>
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            MarketIndices(indices = indices)
        }

        item {
            Text(
                text = "Holdings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            InvestmentSummary(
                modifier = Modifier.fillMaxWidth(),
                data = investmentSummary
            )
        }

        StockHoldings(holdings = holdings)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.StockHoldings(
    holdings: ImmutableList<HoldingDetailData>
) {
    stickyHeader {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        0f to MaterialTheme.colorScheme.surface,
                        0.8f to MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                    )
                )
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Stocks", style = MaterialTheme.typography.titleMedium)
            Text("Current(Invested)", style = MaterialTheme.typography.titleMedium)
        }
    }

    itemsIndexed(
        items = holdings,
        key = { _, data -> data.id },
        contentType = { _, _ -> 1 }
    ) { index, data ->
        HoldingDetail(
            modifier = Modifier.fillMaxWidth(),
            data = data
        )
        if (index != holdings.lastIndex) {
            HorizontalDivider(
                thickness = 0.25.dp,
                color = LightGrey,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun MarketIndices(modifier: Modifier = Modifier, indices: ImmutableList<MarketIndiceData>) {
    // Deliberately not using LazyRow as there won't be more indices even in the real app.
    Row(
        modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        indices.forEach { data ->
            MarketIndice(modifier = Modifier.width(132.dp), data = data)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    StockExampleTheme {
        Surface {
            val indices = persistentListOf(
                MarketIndiceData(
                    title = "Nifty 50",
                    points = "15,000",
                    change = "100",
                    changePercent = "0.67%",
                    changeType = Change.POSITIVE
                ),

                MarketIndiceData(
                    title = "Bank Nifty",
                    points = "35,000",
                    change = "200",
                    changePercent = "0.57%",
                    changeType = Change.NEGATIVE

                ),

                MarketIndiceData(
                    title = "Sensex",
                    points = "50,000",
                    change = "300",
                    changePercent = "0.60%",
                    changeType = Change.POSITIVE

                ),
                MarketIndiceData(
                    title = "Finnifty",
                    points = "25,000",
                    change = "150",
                    changePercent = "0.75%",
                    changeType = Change.POSITIVE
                ),
                MarketIndiceData(
                    title = "BSE Sensex",
                    points = "40,000",
                    change = "250",
                    changePercent = "0.63%",
                    changeType = Change.POSITIVE

                ),
                MarketIndiceData(
                    title = "Nifty 100",
                    points = "10,000",
                    change = "50",
                    changePercent = "0.50%",
                    changeType = Change.POSITIVE
                ),
            )
            val holdings = persistentListOf(
                HoldingDetailData(
                    id = "1",
                    name = "TCS",
                    currentAmount = "₹1,50,000",
                    investedAmount = "₹1,00,000",
                    quantity = "10",
                    change = Change.POSITIVE
                ),
                HoldingDetailData(
                    id = "2",
                    name = "Infosys",
                    currentAmount = "₹1,00,000",
                    investedAmount = "₹1,00,000",
                    quantity = "5",
                    change = Change.NEGATIVE
                ),
                HoldingDetailData(
                    id = "3",
                    name = "Wipro",
                    currentAmount = "₹1,50,000",
                    investedAmount = "₹1,00,000",
                    quantity = "10",
                    change = Change.POSITIVE
                ),
                HoldingDetailData(
                    id = "4",
                    name = "HCL",
                    currentAmount = "₹1,00,000",
                    investedAmount = "₹1,00,000",
                    quantity = "5",
                    change = Change.NEGATIVE
                ),
                HoldingDetailData(
                    id = "5",
                    name = "Tech Mahindra",
                    currentAmount = "₹1,50,000",
                    investedAmount = "₹1,00,000",
                    quantity = "10",
                    change = Change.POSITIVE
                ),
                HoldingDetailData(
                    id = "6",
                    name = "L&T",
                    currentAmount = "₹1,00,000",
                    investedAmount = "₹1,00,000",
                    quantity = "5",
                    change = Change.NEGATIVE
                ),
                HoldingDetailData(
                    id = "7",
                    name = "Reliance",
                    currentAmount = "₹1,50,000",
                    investedAmount = "₹1,00,000",
                    quantity = "10",
                    change = Change.POSITIVE
                )
            )

            MainScreenContent(
                modifier = Modifier,
                indices = indices,
                investmentSummary = InvestmentSummary(
                    currentAmount = "₹ 15,000",
                    investedAmount = "₹ 10,000",
                    returnsSummary = ReturnsSummary(
                        totalReturns = "₹ 5,000",
                        returnsPercent = 50f,
                        change = Change.POSITIVE
                    )
                ),
                holdings = holdings
            )
        }
    }
}