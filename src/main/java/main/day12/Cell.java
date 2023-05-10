package main.day12;

import java.util.List;

public class Cell implements Comparable<Cell>{
    private final int row;
    private final int col;
    private int cost = 0;

    public Cell(int row, int col, int i) {
        this.row = row;
        this.col = col;
        this.cost = i;
    }

    public int getCost() { return cost; }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.getCost(), other.getCost());
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public Cell above() { return new Cell(row - 1, col, cost + 1); }
    public Cell below() { return new Cell(row + 1, col, cost + 1); }
    public Cell left()  { return new Cell(row, col - 1, cost + 1); }
    public Cell right() { return new Cell(row, col + 1, cost + 1); }

    public List<Cell> neighbors() {
        return List.of(above(), below(), left(), right());
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell other = (Cell)o;
        return this.row == other.row && this.col == other.col;
    }


}

