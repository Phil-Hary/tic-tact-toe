package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Cell>> cells = new ArrayList<>();
    Integer size = 0;

    public Board(Integer size) {
        this.size = size;

        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                row.add(null);
            }

            this.cells.add(row);
        }
    }

    public List<List <Cell>> getCells() {
        return this.cells;
    }

    public Integer getSize() {
        return this.size;
    }

    public Cell getCell(Integer row, Integer col) {
        return this.cells.get(row).get(col);
    }

    public void setCell(Cell cell) {
        List<Cell> board_row = this.cells.get(cell.row);
        board_row.set(cell.column, cell);
        this.cells.set(cell.row, board_row);
    }

    public void setCellToNull(Integer row, Integer column) {
        List<Cell> board_row = this.cells.get(row);
        board_row.set(column, null);
        this.cells.set(row, board_row);
    }

    public void displayBoard() {
        for (int idx = 0; idx < this.cells.size(); idx++) {
            for (int jdx = 0; jdx < this.cells.size(); jdx++) {
                Cell cell = this.cells.get(idx).get(jdx);
                
                if(cell == null) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + this.cells.get(idx).get(jdx).getCellSymbol() + " ");
                }
            }
            System.out.println("");
        }
    }
}
