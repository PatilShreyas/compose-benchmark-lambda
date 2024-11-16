package com.example.stockexample.ui.component

data class ReturnsSummary(
    val totalReturns: String,
    val returnsPercent: Float,
    val change: Change
)

data class HoldingDetailData(
    val id: String,
    val name: String,
    val currentAmount: String,
    val investedAmount: String,
    val change: Change,
    val quantity: String,
)

data class MarketIndiceData(
    val title: String,
    val points: String,
    val change: String,
    val changePercent: String,
    val changeType: Change,
)

data class InvestmentSummary(
    val currentAmount: String,
    val investedAmount: String,
    val returnsSummary: ReturnsSummary
)

enum class Change {
    NEUTRAL,
    POSITIVE,
    NEGATIVE,
}