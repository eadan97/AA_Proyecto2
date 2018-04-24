package View;

import Model.Coordinate;
import Model.KenKen;
//import Util.CreateKenKenThread;
import Util.TetrominoType;
import Util.UsualRandomList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainForm {
    private JMenuBar jMenuBar ;
    private JProgressBar progressBar;
    private JPanel generatePanel;
    private JButton generateButton;
    private JPanel contentPane;
    private JSpinner sizeSpinner;

    public MainForm() {
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KenKen kenKenQueue=generateRandomKenKen(Byte.parseByte(sizeSpinner.getValue().toString()));

                //generate KenKen

            }
        });
    }

    public static void main(String[] args){

        JFrame frame = new JFrame("Useless count");
        frame.setContentPane(new MainForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(5,5,19,1);
        sizeSpinner=new JSpinner(spinnerNumberModel);
    }

    public KenKen generateRandomKenKen(byte size){
        KenKen kenKen = new KenKen(size);
        boolean shape=true, latin=true;
        //findTetrominos(kenKen, UsualRandomList.tetrominoTypeRandomList());
        /*for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {

                Iterator<TetrominoType> ttIterator = UsualRandomList.tetrominoTypeRandomList().iterator();
                if (kenKen.isTetromined()!=null)
                    while (ttIterator.hasNext()&&!kenKen.placeTetromino(i, j, ttIterator.next()));

                /*for (TetrominoType tetrominoType :
                        UsualRandomList.tetrominoTypeRandomList()) {
                    if (kenKen.placeTetromino(i, j, tetrominoType)) {
                        if (kenKen.isTetromined()) {
                            kenKen.printTetrominoes();
                            shape=false;
                        }
                        break;
                    }
                }*/
                /*Iterator<Integer> integerIterator=UsualRandomList.integerRandomList((size<10?1:0),(size<10?size:size-1)).iterator();
                if(!kenKen.isLatinSquared())
                    while(integerIterator.hasNext()&&!kenKen.placeNumber(i,j,integerIterator.next()));*/
                /*for (Integer integer:
                        UsualRandomList.integerRandomList((size<10?1:0),(size<10?size:size-1)))
                    if(kenKen.placeNumber(i,j,integer)) {
                        if (kenKen.isLatinSquared())
                            latin = false;
                        break;
                    }*/
        //    }
        //}*/
        kenKen=generateTetrominos(kenKen);
        kenKen=generateLatinSquare(kenKen);
        return kenKen;

    }
    public KenKen generateTetrominos(KenKen kenKen){
        Coordinate coordinate=kenKen.isTetromined();
        if (coordinate==null)
            return kenKen;
        for (TetrominoType tetrominoType:
                UsualRandomList.tetrominoTypeRandomList()) {
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
             UsualRandomList.integerRandomList((kenKen.size<10?1:0),(kenKen.size<10?kenKen.size:kenKen.size-1))) {
            if(kenKen.placeNumber(coordinate.x, coordinate.y, integer)){
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
