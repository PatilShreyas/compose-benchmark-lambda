package com.example.stockexample.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockexample.ui.theme.ErrorRed
import com.example.stockexample.ui.theme.StockExampleTheme
import com.example.stockexample.ui.theme.SuccessGreen

@Composable
fun MarketIndice(
    modifier: Modifier = Modifier,
    data: MarketIndiceData,
) {
    Card(
        modifier,
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(data.title, style = MaterialTheme.typography.labelLarge)
            Text(buildAnnotatedString {
                append(data.points)
                val sign = if (data.changeType == Change.POSITIVE) "+" else "-"
                withStyle(SpanStyle(color = if (data.changeType == Change.POSITIVE) SuccessGreen else ErrorRed)) {
                    append(" $sign${data.change} (${data.changePercent})")
                }
            }, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview
@Composable
fun MarketIndicePositiveChangePreview() {
    StockExampleTheme {
        MarketIndice(
            modifier = Modifier.padding(24.dp),
            MarketIndiceData(
                title = "Nifty 50",
                points = "15,000",
                change = "100",
                changePercent = "0.67%",
                changeType = Change.POSITIVE
            )
        )
    }
}

@Preview
@Composable
fun MarketIndiceNegativeChangePreview() {
    StockExampleTheme {
        MarketIndice(
            modifier = Modifier.padding(24.dp),
            MarketIndiceData(
                title = "Nifty 50",
                points = "15,000",
                change = "100",
                changePercent = "0.67%",
                changeType = Change.NEGATIVE
            )
        )
    }
}