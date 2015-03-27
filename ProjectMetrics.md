# Introduction #


## Metrics script ##

Everything's now done with the metrics script.
```
./scripts/metrics.sh
```

If you still wish to do it manually, here's how it goes.
To count the number of code lines.

With blank lines:
```
find EmaLaeDesktopApplication/ -name "*.java" -type f -exec cat {} \; | wc -l
```

Removing blank lines:
```
find EmaLaeDesktopApplication/ -name "*.java" -type f -exec egrep -v '^$' {} \; | wc -l
```


To count the number of classes:
```
find EmaLaeDesktopApplication/ -name "*.java" -type f -exec ls {} \; | wc -l
```