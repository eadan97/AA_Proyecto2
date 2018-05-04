package Model;

import Util.Operation;
import Util.TetrominoType;

public class Tetromino {
    public TetrominoType type;
    public Coordinate[] positions;
    public Operation operation;
    public int objective;

    public Tetromino(TetrominoType type, Coordinate[] positions) {
        this.type = type;
        this.positions = positions;
    }

    public char getOperation(){
        if (operation == Operation.ADD)
            return '+';

        if (operation == Operation.SUB)
            return '-';

        if (operation == Operation.MUL)
            return 'x';

        if (operation == Operation.DIV)
            return '/';

        if (operation == Operation.MOD)
            return '%';

        if (operation == Operation.EXP)
            return '^';

        return 0;
    }

}
