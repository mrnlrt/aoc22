package main.day12;

import java.util.*;

public class HeightMap {
    private final int[][] map;
    private final int height;
    private final int width;
    private Cell end;
    private Cell start;

    private final Set<Cell> unexplored = new HashSet<>();
    private final Set<Cell> explored = new HashSet<>();

    public HeightMap(List<String> lines) {
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.map = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                char cellChar = lines.get(row).charAt(col);
                if (cellChar == 'S') {
                    cellChar = 'a';
                    start = new Cell(row, col);
                } else if (cellChar == 'E') {
                    cellChar = 'z';
                    end = new Cell(row, col);
                }
                this.map[row][col] = cellChar;
            }
        }
    }

    public Integer getEndCost() {
        return explored.stream()
                .filter(cell -> cell.equals(end))
                .map(Cell::getCost)
                .findFirst()
                .orElse(0);
    }

    public boolean hasUnexplored() {
        return !unexplored.isEmpty();
    }

    public boolean isUnexploredCell(Cell cell) {
        return explored.stream().noneMatch(other -> other.equals(cell)) &&
               unexplored.stream().noneMatch(other -> other.equals(cell));
    }

    public void explore() {
        explore(start);
    }

    public void explore(Cell startCell) {
        unexplored.add(startCell);
        while (hasUnexplored()) {
            Cell node = Collections.min(unexplored);
            unexplored.remove(node);
            explored.add(node);
            getNeighbors(node);
        }
    }

    public boolean isInMap(Cell cell) {
        return cell.getRow() >= 0 && cell.getRow() < height &&
               cell.getCol() >= 0 && cell.getCol() < width;
    }

    public int elevationAt(Cell cell) {
        return map[cell.getRow()][cell.getCol()];
    }

    public void getNeighbors(Cell cell) {
        for (Cell neighborCell : cell.neighbors()) {
            if (isInMap(neighborCell)
                && (elevationAt(neighborCell) - elevationAt(cell)) <= 1
                && isUnexploredCell(neighborCell)
            ) {
                unexplored.add(neighborCell);
            }
        }
    }

    public List<Cell> getPossibleStartCells() {
        List<Cell> startCells = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (map[row][col] == 'a') {
                    startCells.add(new Cell(row, col));
                }
            }
        }
        return startCells;
    }

    public void reset() {
        explored.clear();
    }
}
