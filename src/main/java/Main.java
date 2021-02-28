import java.util.Scanner;

public class Main {

    public static Field FIELD;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var w = sc.nextInt();
        var h = sc.nextInt();
        FIELD = new Field(w, h);
        var mines = sc.nextInt();
        FIELD.initializeField(mines);
        FIELD.recalculateField();
        while (true) {
            if (FIELD.noNumbersHidden()) {
                System.out.println("Win");
                return;
            }
            FIELD.printField();
            var x = sc.nextInt();
            var y = sc.nextInt();
            var cell = FIELD.getCell(x, y);
            if (cell.visible) {
                continue;
            }
            if (cell.mine) {
                throw new RuntimeException("Lose");
            }
            cell.visible = true;
        }
    }
}
