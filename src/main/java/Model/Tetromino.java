package Model;

import Util.Operation;
import Util.TetrominoType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tetromino {
    @XmlElement
    public TetrominoType type;
    @XmlElement
    public Coordinate[] positions;
    @XmlElement
    public Operation operation;
    @XmlElement
    public int objective;

    public Tetromino() {
    }

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
