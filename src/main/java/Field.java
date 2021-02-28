import java.util.Random;

public class Field {
    private final int w;
    private final int h;
    private final Cell[][] cells;

    public Field(int w, int h) {
        this.w = w;
        this.h = h;
        cells = new Cell[w][h];
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x > w || y < 0 || y > h) {
            throw new IllegalArgumentException("Out of bounds");
        }
        return cells[x][y];
    }

    public void initializeField(int mines) {
        var r = new Random();
        while (mines > 0) {
            int x = r.nextInt(w);
            int y = r.nextInt(h);
            if (isMine(cells[x][y])) {
                continue;
            }
            cells[x][y] = Cell.mine();
            --mines;
        }
    }

    public void recalculateField() {
        for (int sx = 0; sx < w; ++sx) {
            for (int sy = 0; sy < h; ++sy) {
                if (isMine(cells[sx][sy])) {
                    continue;
                }
                int value = 0;
                for (int dx = -1; dx <= 1; ++dx) {
                    for (int dy = -1; dy <= 1; ++dy) {
                        int tox = sx + dx;
                        int toy = sy + dy;
                        if (tox >= 0 && tox < w && toy >= 0 && toy < h) {
                            if (tox == sx && toy == sy) {
                                continue;
                            }
                            if (isMine(cells[tox][toy])) {
                                ++value;
                            }
                        }
                    }
                }
                cells[sx][sy] = Cell.number(value);
            }
        }
    }

    public boolean noNumbersHidden() {
        for (int sx = 0; sx < w; ++sx) {
            for (int sy = 0; sy < h; ++sy) {
                if (cells[sx][sy] == null) {
                    return false;
                }
                if (!cells[sx][sy].mine && !cells[sx][sy].visible) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printField() {
        for (int sx = 0; sx < w; ++sx) {
            for (int sy = 0; sy < h; ++sy) {
                if (!cells[sx][sy].visible) {
                    System.out.print('*');
                } else {
                    System.out.print(cells[sx][sy].value);
                }
                // TODO handle flags
            }
            System.out.println();
        }
    }

    private static boolean isMine(Cell cell) {
        return cell != null && cell.mine;
    }
}
