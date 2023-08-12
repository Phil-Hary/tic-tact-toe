package models;

public class Cell {
    Integer row;
    Integer column;
    CellStatus cellStatus;
    Player player;

    public Cell(Integer row, Integer column, Player player) {
        this.row = row;
        this.column = column;
        this.cellStatus = CellStatus.OCCUPIED;
        this.player = player;
    }

    public CellStatus getCellStatus() {
        return this.cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Character getCellSymbol() {
        return this.player.getSymbol();
    }

    public Integer getRow() {
        return this.row;
    }

    public Integer getColumn() {
        return this.column;
    }
}
