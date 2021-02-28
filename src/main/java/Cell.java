public class Cell {

    boolean visible;
    boolean flagged;
    boolean mine;
    int value;

    private Cell(
            boolean visible,
            boolean flagged,
            boolean mine,
            int value
    ) {
        this.visible = visible;
        this.flagged = flagged;
        this.mine = mine;
        this.value = value;
    }

    public static Cell mine() {
        return new Cell(false, false, true, -1);
    }

    public static Cell number(int value) {
        return new Cell(false, false, false, value);
    }
}
