package com.example.stockexample

import com.example.stockexample.ui.component.Change
import com.example.stockexample.ui.component.HoldingDetailData
import com.example.stockexample.ui.component.InvestmentSummary
import com.example.stockexample.ui.component.MarketIndiceData
import com.example.stockexample.ui.component.ReturnsSummary
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

/**
 * Produces fake data for the app infinitely till the time flow is observed via a [produce]
 */
object FakeDataProducer {
    private val fixedIndixes = persistentListOf(
        "NIFTY 50",
        "NIFTY 100",
        "NIFTY Bank",
        "SENSEX",
        "DOW JONES",
        "NASDAQ",
        "FTSE 100",
        "DAX",
        "S&P 500",
    )

    private val fixedHoldings = persistentListOf(
        "INFY",
        "TCS",
        "AAPL",
        "GOOGL",
        "AMZN",
        "MSFT",
        "TSLA",
        "FB",
        "JPM",
        "BAC",
        "WFC",
        "GS",
        "ABC",
        "DEF",
        "GHI",
        "JKL",
        "MNO",
        "PQR",
        "STU",
        "VWX",
        "YZ"
    ).map {
        HoldingDetailData(
            id = it,
            name = it,
            currentAmount = "₹${(100000..200000).random()}",
            investedAmount = "₹${(100000..200000).random()}",
            quantity = (1..100).random().toString(),
            change = randomPositiveOrNegative()
        )
    }

    /**
     * Produces a flow of market indices data every 300ms
     */
    private val marketIndicesAsFlow = flow {
        while (true) {
            delay(300)
            emit(fixedIndixes.map {
                MarketIndiceData(
                    title = it,
                    points = (15000..16000).random().toString(),
                    change = (50..200).random().toString(),
                    changePercent = (0..20).random().toString(),
                    changeType = randomPositiveOrNegative()
                )
            }.toImmutableList())
        }
    }

    /**
     * Produces a flow of holdings data every 500ms
     */
    private val holdingsAsFlow = flow {
        while (true) {
            delay(500)
            emit(fixedHoldings.map {
                it.copy(
                    currentAmount = "₹${(100000..200000).random()}",
                    change = randomPositiveOrNegative()
                )
            }.toImmutableList())
        }
    }

    /**
     * Produces a flow of investment summary data every 250ms
     */
    private val investmentSummaryAsFlow = flow {
        while (true) {
            delay(250)
            emit(
                InvestmentSummary(
                    currentAmount = "₹${(100000..200000).random()}",
                    investedAmount = "₹150000",
                    returnsSummary = ReturnsSummary(
                        totalReturns = "₹${(10000..20000).random()}",
                        returnsPercent = (0..50).random().toFloat(),
                        change = randomPositiveOrNegative()
                    )
                )
            )
        }
    }

    fun produce() = combine(
        marketIndicesAsFlow,
        holdingsAsFlow,
        investmentSummaryAsFlow
    ) { indices, holdings, investmentSummary ->
        Triple(indices, investmentSummary, holdings)
    }

    private fun randomPositiveOrNegative() = listOf(Change.POSITIVE, Change.NEGATIVE).random()
}