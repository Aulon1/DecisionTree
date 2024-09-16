SELECT
CAST(gpuBenchmark as DOUBLE) as gpuBenchmark,
CAST(dedicatedGraphicMemoryCapacity as DOUBLE) as dedicatedGraphicMemoryCapacity,
CAST(refreshRate as DOUBLE) as refreshRate,
CAST(screenResolution as DOUBLE) as screenResolution,
CAST(screenSize as DOUBLE) as screenSize,
CAST(touchScreen as DOUBLE) as touchScreen,
CAST(gpuResolutionScreenSize as DOUBLE) as gpuResolutionScreenSize
    FROM laptops
     where Available=0
      order by id