package com.example.stockexample.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockexample.ui.theme.ErrorRed
import com.example.stockexample.ui.theme.StockExampleTheme
import com.example.stockexample.ui.theme.SuccessGreen

@Composable
fun InvestmentSummary(
    modifier: Modifier = Modifier,
    dataProvider: () -> InvestmentSummary
) {
    val data = dataProvider()
    Card(
        modifier.fillMaxWidth(),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                AmountDetail(
                    title = "Current Amount",
                    amount = data.currentAmount,
                )
                AmountDetail(
                    title = "Invested Amount",
                    amount = data.investedAmount,
                )
            }

            AmountDetail(
                title = "Returns",
                amount = "${data.returnsSummary.totalReturns} (${data.returnsSummary.returnsPercent}%)",
                change = data.returnsSummary.change
            )
        }
    }
}

@Composable
fun AmountDetail(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    change: Change = Change.NEUTRAL
) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.labelSmall)
        Text(
            buildAnnotatedString {
                append(amount)
            },
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = when (change) {
                Change.NEUTRAL -> MaterialTheme.colorScheme.onSurface
                Change.POSITIVE -> SuccessGreen
                Change.NEGATIVE -> ErrorRed
            }
        )
    }
}

@Preview
@Composable
fun InvestmentSummaryPositivePreview() {
    StockExampleTheme {
        InvestmentSummary(
            modifier = Modifier.padding(24.dp),
            dataProvider = {
                InvestmentSummary(
                    currentAmount = "₹ 15,000",
                    investedAmount = "₹ 10,000",
                    returnsSummary = ReturnsSummary(
                        totalReturns = "₹ 5,000",
                        returnsPercent = 50f,
                        change = Change.POSITIVE
                    )
                )
            }
        )
    }
}

@Preview
@Composable
fun InvestmentSummaryNegativePreview() {
    StockExampleTheme {
        InvestmentSummary(
            modifier = Modifier.padding(24.dp),
            dataProvider = {
                InvestmentSummary(
                    currentAmount = "₹ 5,000",
                    investedAmount = "₹ 15,000",
                    returnsSummary = ReturnsSummary(
                        totalReturns = "₹ 5,000",
                        returnsPercent = 50f,
                        change = Change.NEGATIVE
                    )
                )
            }
        )
    }
}

@Preview
@Composable
fun InvestmentSummaryNeutralPreview() {
    StockExampleTheme {
        InvestmentSummary(
            modifier = Modifier.padding(24.dp),
            dataProvider = {
                InvestmentSummary(
                    currentAmount = "₹ 10,000",
                    investedAmount = "₹ 10,000",
                    returnsSummary = ReturnsSummary(
                        totalReturns = "₹ 0",
                        returnsPercent = 0.0f,
                        change = Change.NEUTRAL
                    )
                )
            }
        )
    }
}