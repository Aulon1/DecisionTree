SELECT
CAST(ram as DOUBLE) as ram,
CAST(ramTypeTokenized AS DOUBLE) as ramTypeTokenized,
CAST(baseClockSpeed as DOUBLE) as baseClockSpeed,
CAST(turboClockSpeed as DOUBLE) as turboClockSpeed,
CAST(storage as DOUBLE) as storage,
CAST(ssd as DOUBLE) as ssd,
CAST(cpuRanking as DOUBLE) as cpuRanking,
CAST(ramStorageCpuEvaluation as DOUBLE) as ramStorageCpuEvaluation
     FROM laptops
      where Available=0
       order by id