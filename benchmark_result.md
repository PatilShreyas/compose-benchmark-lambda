# Benchmark results

Benchmark tests were performed on the three devices -

## 1. Device - 2.0 GB RAM, Octa core, Android 11

**Meanings:**
- Before: Implementation of the compose UI having direct state propagated through the composables
- After: Implementation of the compose UI having state propogated through the composables via lambda.

### Iteration 1

```
- Before
StockScreenBenchmark_startup
frameCount                     min    109.0,   median    116.5,   max    121.0
gfxFrameJankPercent            min     72.1,   median     78.2,   max     83.5
gfxFrameTime50thPercentileMs   min     19.0,   median     19.0,   max     21.0
gfxFrameTime90thPercentileMs   min     31.0,   median     32.0,   max     34.0
gfxFrameTime95thPercentileMs   min     38.0,   median     48.0,   max     53.0
gfxFrameTime99thPercentileMs   min    150.0,   median    150.0,   max    150.0
gfxFrameTotalCount             min    107.0,   median    114.5,   max    119.0
memoryHeapSizeMaxKb            min 10,256.0,   median 10,400.0,   max 10,735.0
frameDurationCpuMs             P50      19.1,   P90      32.8,   P95      40.5,   P99     171.2

- After 
StockScreenBenchmark_startup
frameCount                     min   102.0,   median   111.5,   max   118.0
gfxFrameJankPercent            min    44.8,   median    46.8,   max    49.5
gfxFrameTime50thPercentileMs   min    12.0,   median    13.0,   max    16.0
gfxFrameTime90thPercentileMs   min    26.0,   median    27.0,   max    28.0
gfxFrameTime95thPercentileMs   min    29.0,   median    38.0,   max    53.0
gfxFrameTime99thPercentileMs   min   150.0,   median   150.0,   max   200.0
gfxFrameTotalCount             min   104.0,   median   110.0,   max   116.0
memoryHeapSizeMaxKb            min 8,134.0,   median 9,235.0,   max 9,345.0
frameDurationCpuMs             P50     13.3,   P90     27.7,   P95     36.1,   P99    177.6
```

### Iteration 2

```
- Before
StockScreenBenchmark_startup
frameCount                     min    108.0,   median    113.0,   max    118.0
gfxFrameJankPercent            min     73.1,   median     78.2,   max     82.9
gfxFrameTime50thPercentileMs   min     19.0,   median     19.5,   max     20.0
gfxFrameTime90thPercentileMs   min     32.0,   median     32.0,   max     34.0
gfxFrameTime95thPercentileMs   min     42.0,   median     48.0,   max     53.0
gfxFrameTime99thPercentileMs   min    150.0,   median    150.0,   max    150.0
gfxFrameTotalCount             min    106.0,   median    111.0,   max    119.0
memoryHeapSizeMaxKb            min 10,210.0,   median 10,372.5,   max 10,497.0
frameDurationCpuMs             P50      19.0,   P90      33.2,   P95      40.2,   P99     172.0

- After
StockScreenBenchmark_startup
frameCount                     min   103.0,   median   110.0,   max   121.0
gfxFrameJankPercent            min    44.0,   median    46.4,   max    49.1
gfxFrameTime50thPercentileMs   min    12.0,   median    13.0,   max    15.0
gfxFrameTime90thPercentileMs   min    26.0,   median    27.0,   max    29.0
gfxFrameTime95thPercentileMs   min    30.0,   median    39.0,   max    53.0
gfxFrameTime99thPercentileMs   min   150.0,   median   150.0,   max   200.0
gfxFrameTotalCount             min   103.0,   median   108.0,   max   119.0
memoryHeapSizeMaxKb            min 8,076.0,   median 8,841.5,   max 9,694.0
frameDurationCpuMs             P50     12.9,   P90     27.6,   P95     34.5,   P99    197.8
```

### Iteration 3 (R8 Enabled - Full Mode)

```
- Before	
StockScreenBenchmark_startup
frameCount                     min   114.0,   median   119.0,   max   126.0
gfxFrameJankPercent            min    47.5,   median    51.5,   max    53.9
gfxFrameTime50thPercentileMs   min    16.0,   median    16.5,   max    17.0
gfxFrameTime90thPercentileMs   min    25.0,   median    25.0,   max    26.0
gfxFrameTime95thPercentileMs   min    26.0,   median    28.5,   max    32.0
gfxFrameTime99thPercentileMs   min   125.0,   median   129.0,   max   150.0
gfxFrameTotalCount             min   116.0,   median   120.5,   max   127.0
memoryHeapSizeMaxKb            min 9,209.0,   median 9,321.0,   max 9,402.0
frameDurationCpuMs             P50     16.9,   P90     25.9,   P95     29.3,   P99    136.3
	
- After	
StockScreenBenchmark_startup
frameCount                     min   112.0,   median   119.5,   max   125.0
gfxFrameJankPercent            min    43.3,   median    45.1,   max    49.1
gfxFrameTime50thPercentileMs   min    11.0,   median    12.0,   max    16.0
gfxFrameTime90thPercentileMs   min    21.0,   median    22.0,   max    22.0
gfxFrameTime95thPercentileMs   min    23.0,   median    27.0,   max    32.0
gfxFrameTime99thPercentileMs   min   125.0,   median   132.0,   max   150.0
gfxFrameTotalCount             min   114.0,   median   121.0,   max   127.0
memoryHeapSizeMaxKb            min 7,259.0,   median 7,308.5,   max 7,372.0
frameDurationCpuMs             P50     12.3,   P90     22.2,   P95     31.3,   P99    146.1
```

---

## 2. Device - Moto G20 (Firebase Test Lab Device - Android 11, 4 GB RAM)

```
- Before
StockScreenBenchmark_startup
frameCount                     min    101.0,   median    108.0,   max    115.0
gfxFrameJankPercent            min     22.6,   median     28.3,   max     31.4
gfxFrameTime50thPercentileMs   min     12.0,   median     13.0,   max     13.0
gfxFrameTime90thPercentileMs   min     18.0,   median     19.0,   max     20.0
gfxFrameTime95thPercentileMs   min     20.0,   median     21.5,   max     25.0
gfxFrameTime99thPercentileMs   min     77.0,   median     93.0,   max    350.0
gfxFrameTotalCount             min    103.0,   median    109.0,   max    115.0
memoryHeapSizeMaxKb            min 10,505.0,   median 10,729.5,   max 11,040.0
frameDurationCpuMs             P50      13.3,   P90      19.1,   P95      21.2,   P99      99.6

- After
StockScreenBenchmark_startup
frameCount                     min    101.0,   median    109.5,   max    116.0
gfxFrameJankPercent            min     10.6,   median     14.9,   max     18.8
gfxFrameTime50thPercentileMs   min      9.0,   median     10.0,   max     11.0
gfxFrameTime90thPercentileMs   min     16.0,   median     17.0,   max     18.0
gfxFrameTime95thPercentileMs   min     18.0,   median     23.0,   max     24.0
gfxFrameTime99thPercentileMs   min     77.0,   median     81.0,   max     89.0
gfxFrameTotalCount             min    103.0,   median    109.5,   max    114.0
memoryHeapSizeMaxKb            min  9,118.0,   median 10,442.5,   max 10,640.0
frameDurationCpuMs             P50      10.4,   P90      17.5,   P95      21.9,   P99      88.4
```

---

## 3. Device - Emulator (Pixel 8a)

```
- Before 
StockScreenBenchmark_startup
frameCount                     min    110.0,   median    114.5,   max    122.0
gfxFrameJankPercent            min      0.0,   median      0.0,   max      0.0
gfxFrameTime50thPercentileMs   min      6.0,   median      7.0,   max      8.0
gfxFrameTime90thPercentileMs   min     11.0,   median     12.0,   max     15.0
gfxFrameTime95thPercentileMs   min     12.0,   median     13.5,   max     18.0
gfxFrameTime99thPercentileMs   min     15.0,   median     19.5,   max     38.0
gfxFrameTotalCount             min    110.0,   median    115.0,   max    123.0
memoryHeapSizeMaxKb            min  9,850.0,   median 10,107.0,   max 10,251.0
frameDurationCpuMs             P50       6.5,   P90      10.3,   P95      11.7,   P99      28.6
frameOverrunMs                 P50      -9.0,   P90      -4.2,   P95      -2.3,   P99      19.2

- After
StockScreenBenchmark_startup
frameCount                     min   110.0,   median   117.5,   max   121.0
gfxFrameJankPercent            min     0.0,   median     0.0,   max     0.0
gfxFrameTime50thPercentileMs   min     6.0,   median     6.0,   max     7.0
gfxFrameTime90thPercentileMs   min     9.0,   median    10.0,   max    11.0
gfxFrameTime95thPercentileMs   min    11.0,   median    12.0,   max    13.0
gfxFrameTime99thPercentileMs   min    14.0,   median    15.5,   max    21.0
gfxFrameTotalCount             min   112.0,   median   118.5,   max   122.0
memoryHeapSizeMaxKb            min 7,630.0,   median 7,677.0,   max 7,696.0
frameDurationCpuMs             P50      5.6,   P90      9.1,   P95     10.2,   P99     19.8
frameOverrunMs                 P50     -9.4,   P90     -5.6,   P95     -4.3,   P99      4.2
```