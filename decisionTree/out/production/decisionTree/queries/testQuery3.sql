SELECT
CAST(weight as DOUBLE) as weight,
CAST(batteryBackup as DOUBLE) as batteryBackup,
CAST(bateryWeightUserRating as DOUBLE) as bateryWeightUserRating
      FROM laptops
       where Available=1
        order by id