#!/bin/bash
# Compute some project metrics:
#   - class count
#   - line count

projects="EmaLaeCommon  EmaLaeDesktopApplication  EmaLaeServerApplication"

echo -e "== Projects class count ==\n"
for project in $projects
do
    echo "$project classes:"
    total=`find "$project/" -name "*.java" -type f -exec ls {} \; | wc -l`
    tests=`find "$project/test" -name "*.java" -type f -exec ls {} \; | wc -l`
    echo -e "total: $total (tests: $tests)\n"
done

echo -e "\n"

echo -e "== Projects line count ==\n"
for project in $projects
do
    echo "$project lines:"
    total=`find "$project/" -name "*.java" -type f -exec cat {} \; | wc -l`
    tests=`find "$project/test/" -name "*.java" -type f -exec cat {} \; | wc -l`
    echo -e "total: $total (tests: $tests)\n"
done


