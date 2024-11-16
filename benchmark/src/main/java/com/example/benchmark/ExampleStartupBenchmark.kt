package com.example.benchmark

import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingGfxInfoMetric
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MemoryCountersMetric
import androidx.benchmark.macro.MemoryUsageMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StockScreenBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.example.stockexample",
        metrics = listOf(
            FrameTimingMetric(),
            FrameTimingGfxInfoMetric(),
            MemoryCountersMetric(),
            MemoryUsageMetric(
                mode = MemoryUsageMetric.Mode.Max,
                subMetrics = listOf(
                    MemoryUsageMetric.SubMetric.HeapSize,
                    MemoryUsageMetric.SubMetric.Gpu
                )
            )
        ),
        iterations = 10,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        Thread.sleep(10000)
    }
}