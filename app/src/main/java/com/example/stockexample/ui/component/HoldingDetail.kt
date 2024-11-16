package com.example.stockexample.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockexample.ui.theme.ErrorRed
import com.example.stockexample.ui.theme.StockExampleTheme
import com.example.stockexample.ui.theme.SuccessGreen


@Composable
fun HoldingDetail(modifier: Modifier, data: HoldingDetailData) {
    Row(modifier, horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        HoldingStockInfo(
            name = data.name, quantity = data.quantity
        )
        HoldingAmountInfo(
            currentAmount = data.currentAmount,
            investedAmount = data.investedAmount,
            change = data.change
        )
    }
}

@Composable
fun HoldingStockInfo(modifier: Modifier = Modifier, name: String, quantity: String) {
    Column(modifier) {
        Text(text = name, style = MaterialTheme.typography.bodyMedium)
        Text(text = quantity, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun HoldingAmountInfo(
    modifier: Modifier = Modifier, currentAmount: String, investedAmount: String, change: Change
) {
    Column(modifier) {
        Text(
            text = currentAmount,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = when (change) {
                Change.POSITIVE -> SuccessGreen
                Change.NEGATIVE -> ErrorRed
                Change.NEUTRAL -> MaterialTheme.colorScheme.onSurface
            }
        )
        Text(text = investedAmount, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun HoldingDetailPositiveReturnPreview() {
    StockExampleTheme {
        HoldingDetail(
            modifier = Modifier.fillMaxWidth(), data = HoldingDetailData(
                id = "1",
                name = "TCS",
                currentAmount = "₹1,50,000",
                investedAmount = "₹1,00,000",
                quantity = "10",
                change = Change.POSITIVE
            )
        )
    }
}

@Preview
@Composable
fun HoldingDetailNegativeReturnPreview() {
    StockExampleTheme {
        HoldingDetail(
            modifier = Modifier.fillMaxWidth(), data = HoldingDetailData(
                id = "1",
                name = "TCS",
                currentAmount = "₹1,50,000",
                investedAmount = "₹1,00,000",
                quantity = "10",
                change = Change.NEGATIVE
            )
        )
    }
}