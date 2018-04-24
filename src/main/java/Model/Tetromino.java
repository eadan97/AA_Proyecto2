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

}
