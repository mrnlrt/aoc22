package main.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RockPaths {
    List<List<Point>> rockPaths = new ArrayList<>();
    int minWidth;

    public RockPaths(List<String> input) {
        for (String path : input) {
            List<Point> rockPath = Arrays.stream(path.split(" -> "))
                    .map(Point::new)
                    .collect(Collectors.toList());
            rockPaths.add(rockPath);
        }
        getMinWidth();
    }

    private void getMinWidth() {
        minWidth = rockPaths.stream()
                .mapToInt(path ->
                        path.stream()
                                .mapToInt(point -> point.x)
                                .min().getAsInt()

                )
                .min().getAsInt();
    }

    public int getHeight() {
        return rockPaths.stream()
                       .mapToInt(path ->
                               path.stream()
                                       .mapToInt(point -> point.y)
                                       .max().getAsInt()

                       )
                       .max().getAsInt() ;
    }

    public int getWidth() {
        return getMaxWidth();
    }

    private int getMaxWidth() {
        return rockPaths.stream()
                .mapToInt(path ->
                        path.stream()
                                .mapToInt(point -> point.x)
                                .max().getAsInt()

                )
                .max().getAsInt();
    }

    void shift() {
        rockPaths.forEach(
                path -> path.forEach(point -> point.shiftX(minWidth))
        );
    }
}
