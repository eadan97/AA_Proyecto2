package View;

import Model.Coordinate;
import Model.KenKen;
import Model.Tetromino;
import Util.Operation;
import Util.TetrominoType;
import Util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JMenuBar jMenuBar ;
    private JProgressBar progressBar;
    private JPanel generatePanel;
    private JButton generateButton;
    private JPanel contentPane;
    private JSpinner sizeSpinner;
    private JTable table1;
    private JButton solveButton;
    KenKen kenKen;

    public MainForm() {
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateRandomKenKen(Byte.parseByte(sizeSpinner.getValue().toString()));
            }
        });
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Todo:Load kenKen
                kenKen.clearCurrentData();
                kenKen=solveKenKen(kenKen);
            }
        });
    }

    private KenKen solveKenKen(KenKen pKenKen) {
        Coordinate coordinate=pKenKen.isSolved();
        if (coordinate==null)
            return pKenKen;
        for (Tetromino tetromino:
             pKenKen.tetrominos) {
            

        }
        for (Integer integer:
                Util.integerRandomList((pKenKen.size<10?1:0),(pKenKen.size<10?pKenKen.size:pKenKen.size-1))) {
            if(pKenKen.placeNumberInSolution(coordinate.x, coordinate.y, integer)){
                KenKen ken=solveKenKen(new KenKen(pKenKen));
                if (ken!=null)
                    return ken;
            }
        }
        /*for (int k = 0; k < square.length; k++) {
            if(square[coordinate.x][k]==integer.byteValue()||square[k][coordinate.y]==integer.byteValue())
                return false;
        }*/
        return null;
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Useless count");
        frame.setContentPane(new MainForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,500);
    }

    private void createUIComponents() {
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(5,5,19,1);
        sizeSpinner=new JSpinner(spinnerNumberModel);
    }

    public KenKen generateRandomKenKen(byte size){
        kenKen = new KenKen(size);
        boolean shape=true, latin=true;
        //findTetrominos(kenKen, Util.tetrominoTypeRandomList());
        /*for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {

                Iterator<TetrominoType> ttIterator = Util.tetrominoTypeRandomList().iterator();
                if (kenKen.isTetromined()!=null)
                    while (ttIterator.hasNext()&&!kenKen.placeTetromino(i, j, ttIterator.next()));

                /*for (TetrominoType tetrominoType :
                        Util.tetrominoTypeRandomList()) {
                    if (kenKen.placeTetromino(i, j, tetrominoType)) {
                        if (kenKen.isTetromined()) {
                            kenKen.printTetrominoes();
                            shape=false;
                        }
                        break;
                    }
                }*/
                /*Iterator<Integer> integerIterator=Util.integerRandomList((size<10?1:0),(size<10?size:size-1)).iterator();
                if(!kenKen.isLatinSquared())
                    while(integerIterator.hasNext()&&!kenKen.placeNumberInLatinSquare(i,j,integerIterator.next()));*/
                /*for (Integer integer:
                        Util.integerRandomList((size<10?1:0),(size<10?size:size-1)))
                    if(kenKen.placeNumberInLatinSquare(i,j,integer)) {
                        if (kenKen.isLatinSquared())
                            latin = false;
                        break;
                    }*/
        //    }
        //}*/
        kenKen=generateTetrominos(kenKen);
        kenKen=generateLatinSquare(kenKen);
        kenKen=generateOperations(kenKen);
        return kenKen;

    }

    private KenKen generateOperations(KenKen kenKen) {
        Tetromino tetromino=kenKen.isFullWithOperations();
        if (tetromino==null)
            return kenKen;
        else if (tetromino.type==TetrominoType.DOT_0) {
            kenKen.generateObjectiveWith(tetromino, Operation.EXP);
        }
        else if (tetromino.type==TetrominoType.DOTDOT_0||
                tetromino.type==TetrominoType.DOTDOT_1) {
            kenKen.generateObjectiveWith(tetromino, Operation.MOD);
        }
        else{
            Operation operation = Util.randomOperationFour();
            kenKen.generateObjectiveWith(tetromino, operation);
        }
        KenKen ken=generateOperations(new KenKen(kenKen));
        return ken;

    }

    public KenKen generateTetrominos(KenKen kenKen){
        Coordinate coordinate=kenKen.isTetromined();
        if (coordinate==null)
            return kenKen;
        for (TetrominoType tetrominoType:
                Util.tetrominoTypeRandomList()) {
            if(kenKen.placeTetromino(coordinate.x, coordinate.y, tetrominoType)){
                KenKen ken=generateTetrominos(new KenKen(kenKen));
                if (ken!=null)
                    return ken;
            }
        }
        return null;

    }
    public KenKen generateLatinSquare(KenKen kenKen){
        Coordinate coordinate=kenKen.isLatinSquared();
        if (coordinate==null)
            return kenKen;
        for (Integer integer:
             Util.integerRandomList((kenKen.size<10?1:0),(kenKen.size<10?kenKen.size:kenKen.size-1))) {
            if(kenKen.placeNumberInLatinSquare(coordinate.x, coordinate.y, integer)){
                KenKen ken=generateLatinSquare(new KenKen(kenKen));
                if (ken!=null)
                    return ken;
            }
        }
        /*for (int k = 0; k < square.length; k++) {
            if(square[coordinate.x][k]==integer.byteValue()||square[k][coordinate.y]==integer.byteValue())
                return false;
        }*/
        return null;
        
    }
    
}
