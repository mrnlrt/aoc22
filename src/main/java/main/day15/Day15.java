package main.day15;

import org.apache.commons.lang3.Range;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Collections.sort;

public class Day15 {
    public static final int MAX_AREA_SEARCH = 4000000;
    Set<Integer> visitedInRow = new HashSet<>();

    public long part1(List<String> input, int row) {
        List<Point> sensors = input.stream()
                .map(Point::new)
                .toList();
        sensors.forEach(sensor -> {
            visitedInRow.addAll(sensor.rowDistance(row));
        });

        sensors.forEach(sensor -> {
            if (sensor.y == row) {
                visitedInRow.remove(sensor.x);
            }
            if (sensor.beacon.y == row) {
                visitedInRow.remove(sensor.beacon.x);
            }
        });
        return visitedInRow.size();
    }


    public long part2(List<String> input) {
        List<Point> sensors = input.stream()
                .map(Point::new)
                .toList();
        for (int i = 11; i <= MAX_AREA_SEARCH; i++) {
            List<Range<Integer>> collect = new ArrayList<>();
            for (Point sensor : sensors) {
                int xMin = sensor.getXMin(i);
                int xMax = sensor.getXMax(i);
                if (xMin < xMax) {
                    collect.add(Range.between(max(xMin, 0), min(xMax, MAX_AREA_SEARCH)));
                }
            }
            sort(collect, Comparator.comparingInt(Range::getMinimum));
            collect = simplifyRanges(collect);
            if (collect.size() > 1) {
                long x = collect.get(0).getMaximum() + 1;
                return x  * 4000000 + i;
            }
        }
        return 0;
    }

    public List<Range<Integer>> simplifyRanges(List<Range<Integer>> ranges) {
        List<Range<Integer>> simplified = new ArrayList<>();
        simplified.add(ranges.get(0));
        for (int i = 1; i < ranges.size(); i++) {
            Range<Integer> current = ranges.get(i);
            int lastIndex = simplified.size() - 1;
            Range<Integer> last = simplified.get(lastIndex);
            if (last.isOverlappedBy(current)) {
                simplified.set(lastIndex, Range.between(last.getMinimum(), max(last.getMaximum(), current.getMaximum())));
            } else {
                simplified.add(current);
            }
        }
        return simplified;
    }


}


