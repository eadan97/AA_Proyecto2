package Model;

import Util.Operation;
import Util.TetrominoType;

public class Tetromino {
    TetrominoType type;
    Coordinate[] positions;
    Operation operation;
    short objective;

    public Tetromino(TetrominoType type, Coordinate[] positions) {
        this.type = type;
        this.positions = positions;
    }
}
