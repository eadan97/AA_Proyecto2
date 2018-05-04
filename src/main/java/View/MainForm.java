package View;

import Model.Coordinate;
import Model.KenKen;
import Model.Tetromino;
import Util.Operation;
import Util.TetrominoType;
import Util.Util;
import Util.MarshallerUtil;
import com.sun.jmx.snmp.agent.SnmpMibAgent;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainForm {
    private JMenuBar jMenuBar ;
    private JProgressBar progressBar;
    private JPanel generatePanel;
    private JButton generateButton;
    private JPanel contentPane;
    private JSpinner sizeSpinner;
    private JButton solveButton;
    private JPanel kenkenPanel;
    private JButton btnLoad;
    private JButton btnSave;
    private JPanel solutionPanel;
    private JLabel lblThreads;
    private JSpinner threadSpinner;
    KenKen kenKen;
    ThreadPoolExecutor executor;
    boolean foundSolution;
    public MainForm() {
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateRandomKenKen(Byte.parseByte(sizeSpinner.getValue().toString()));

                int kkSize = (Integer)sizeSpinner.getValue();
                int[] valuesDeffault = new int[kkSize*kkSize];
                drawKenKen(kkSize , kenKen , kenkenPanel , valuesDeffault);
            }
        });

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cantidadHilos=(Integer)threadSpinner.getValue();;
                kenKen.clearCurrentData();

                executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cantidadHilos);
                kenKen= solveKenKen(kenKen);
                if (kenKen.equals(null))
                {
                    System.out.println("I AM NULL!");
                }
                kenKen.printSolution();

                int index = 0;
                int[] solution = new int[kenKen.currentData.length *kenKen.currentData.length];
                for (int i = 0; i < kenKen.currentData.length ; i++)
                {
                    for (int j = 0 ; j < kenKen.currentData[i].length ; j++)
                    {
                        solution[index] = kenKen.currentData[i][j];
                        index++;
                    }
                }
                drawKenKen((int)kenKen.size , kenKen , solutionPanel , solution);

            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarshallerUtil.save("savedTetromino.st", kenKen);
            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kenKen= (KenKen) MarshallerUtil.load("savedTetromino.st", KenKen.class);
            }
        });
    }

    public void drawKenKen(int kkSize , KenKen kenKen , JPanel kenkenPanel , int[] values)
    {
        System.out.println("Tetrominos");
        kenKen.printTetrominoes();
        System.out.println("Latin Square");
        kenKen.printSquare();
        System.out.println("Current Data");
        kenKen.printCurrentData();

        int realSize = kkSize * kkSize;
        System.out.println("Generating a KenKen of size: " + realSize);

        JLabel labels[] = new JLabel[realSize];
        JLabel smallLabels[] = new JLabel[kenKen.tetrominos.size()];
        placeSmallLabels(smallLabels);

        setUpPanel(kkSize , kenkenPanel);

        int counter = 0;
        int x = 100;
        int y = 10;

        int cursor = 0; //ayuda a insertar en lista de tetrominos grande
        int[] tetros = new int[realSize];
        for (int n = 0; n < kenKen.tetrominosMatrix.length ; n++)
        {
            for (int m = 0 ; m < kenKen.tetrominosMatrix[n].length ; m++)
            {
                tetros[cursor] = kenKen.tetrominosMatrix[n][m];
                cursor++;
            }
        }

        int iterator = 0; //ayuda a iterar sobre la lista grande

        int isPlacedHelper = 0; //Coloca los que ya tienen
        int[] isPlaced = new int[kenKen.tetrominos.size()];

        int currentTetrominoLabel = 0; //itera sobre botones
        //PLACING ITERATION
        for (int i = 0; i < labels.length ; i++)
        {
            int indicator = kkSize;

            //"lbl" + 1
            JLabel currentLabel = new JLabel(" ");
            currentLabel.setBounds(x , y , 50 , 50);

            currentLabel.setLayout(new OverlayLayout(currentLabel));

            JLabel small = smallLabels[currentTetrominoLabel];

            x += 100;
            counter++;

            //System.out.println("Indicator: " + indicator + " y " + "Counter: " + counter);
            if (counter == indicator)
            {
                //System.out.println("Cambio de línea");
                x = 100;
                y+= 100;
                counter = 0;
            }

            setUpLabel(currentLabel , values[i]);
            //System.out.println("A new label has been created. ID: " + i);
            labels[i] = currentLabel;

            int currentTetro = tetros[iterator];
            System.out.println("Current Cage: " + currentTetro + " , " + "Is contained? " + containsNumber(isPlaced,currentTetro));
            if (!containsNumber(isPlaced , currentTetro))
            {
                System.out.println("ADDED: " + currentTetro);
                isPlaced[isPlacedHelper] = currentTetro; //add it
                currentLabel.add(small); //Pasar de label
                isPlacedHelper++; //Colocar en ya colocados
                iterator++; //Tetros
                if (currentTetrominoLabel < smallLabels.length - 1)
                    currentTetrominoLabel++;
            }

            else
            {
                iterator++;
            }


            kenkenPanel.add(currentLabel);
        }

        paintKenKen(labels);

        kenkenPanel.validate();
        kenkenPanel.repaint();

    }

    public void printLista(int[] lista)
    {
        for (int i = 0 ; i < lista.length ; i++)
        {
            System.out.println(lista[i]);
        }
    }

    public void setUpLabel(JLabel currentLabel , int id)
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        currentLabel.setOpaque(true);
        currentLabel.setBorder(border);
        currentLabel.setText("" + id); //"id:" + id
        currentLabel.setVerticalAlignment(SwingConstants.CENTER);
        currentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentLabel.setBackground(Color.WHITE);
    }

    public void setUpPanel(int kkSize , JPanel kenkenPanel)
    {
        kenkenPanel.setLayout(new GridLayout(kkSize , kkSize));
        kenkenPanel.setSize(kkSize * 50, kkSize * 50);
        kenkenPanel.setOpaque(false);
        kenkenPanel.setBackground(Color.yellow);
    }

    public void paintKenKen(JLabel[] labels)
    {
        int[][] colores = {  {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0}
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181}
                , {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{230,230,250} , {240,255,240} , {192,192,192} , {128,128,128} , {128,0,0} //2
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181},
                {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} // 3
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181}
                , {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} //4
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181}
                , {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} //5
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181}
                , {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} //6
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181},
                {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} // 7
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181}
                , {255,0,0} , {255,0,0} , {0,255,0} , {0,0,255} , {255,255,0} , {165,42,42}
                ,{0,255,255} , {255,0,255} , {192,192,192} , {128,128,128} , {128,0,0} //8
                , {128,128,0} , {0,128,0} , {128,0,128} , {0,128,128} , {0,0,128} , {255,127,80}
                , {250,128,114} , {255,69,0} , {255,140,0} , {255,165,0} , {184,134,11} , {218,165,32}
                , {189,183,107} , {128,128,0} , {124,252,0} , {173,255,47} , {34,139,34} , {144,238,144}
                , {0,255,127} , {46,139,87} , {102,205,170} , {0,128,128} , {176,224,230} , {0,191,255}
                , {147,112,219} , {139,0,139} , {186,85,211} , {216,191,216} , {238,130,238} , {255,20,147}
                , {245,222,179} , {245,245,220} , {210,105,30} , {222,184,135} , {210,180,140} , {255,228,181} };

        int counter = 0;
        for (int i = 0 ; i < kenKen.tetrominosMatrix.length; i++)
        {
            for (int j = 0 ; j < kenKen.tetrominosMatrix[i].length ; j++ )
            {
                int tetrominoID = kenKen.tetrominosMatrix[i][j];
                Color colorTetromino = new Color(colores[tetrominoID][0] , colores[tetrominoID][1] , colores[tetrominoID][2]);
                labels[counter].setBackground(colorTetromino);
                //labels[counter].setBorder(BorderFactory.createBevelBorder(0,colorTetromino,colorTetromino));
                counter++;

            }
        }
    }

    public void placeSmallLabels(JLabel[] smallLabels)
    {
        Font controlFont = new Font("Dialog" , Font.BOLD , 10);
        for (int i = 0 ; i < smallLabels.length ; i++)
        {
            Tetromino currentTetromino = kenKen.tetrominos.get(i);
            JLabel currentSmallLabel = new JLabel("" + currentTetromino.objective + currentTetromino.getOperation());
            //currentSmallLabel.setBounds(x + 2 , y + 5 , 10 , 10);
            currentSmallLabel.setFont(controlFont);
            System.out.println("Creating label: " + i + " " + currentTetromino.objective + " , " + currentSmallLabel.getText() );
            smallLabels[i] = currentSmallLabel;
        }

        //Placement logic
        /*ArrayList<Integer> visited = new ArrayList<>();

        for (int i = 0; i < kenKen.tetrominosMatrix.length ; i++)
        {
            for (int j = 0 ; j < kenKen.tetrominosMatrix[i].length ; j++)
            {
                if (!visited.contains(kenKen.tetrominosMatrix[i][j]))
                {
                    visited.add((int)kenKen.tetrominosMatrix[i][j]);
                }
            }
        }*/
    }

    public boolean containsNumber(int[] list, int number)
    {
        for (int i = 0 ; i < list.length ; i++)
        {
            if (list[i] == number)
                return true;

        }
        return false;
    }
    private KenKen solveKenKen(KenKen pKenKen) {
        Tetromino tetromino=pKenKen.isSolved();
        if (tetromino==null)
            return pKenKen;

        List<Future<KenKen>> futures = new ArrayList<>();
        for (Integer integer:
                Util.integerList((pKenKen.size<10?1:0),(pKenKen.size<10?pKenKen.size:pKenKen.size-1))) {
            if(pKenKen.placeNumberInSolution(tetromino, integer)){
                KenKen ken= null;
                //System.out.println(Thread.currentThread().getId());
                if (executor.getActiveCount()<executor.getCorePoolSize()){
                    futures.add(executor.submit(() -> solveKenKen(new KenKen(pKenKen))));
                    /*try {
                        ken = kenKenFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }*/

                }else{
                    ken=solveKenKen(new KenKen(pKenKen));
                }

                if (ken!=null)
                    return ken;
                pKenKen.removeLastMove();
            }

        }
        for (Future<KenKen> future :
                futures) {
            try {
                KenKen kkf=future.get();
                if (kkf != null)
                    return kkf;
            }catch (Exception e){}
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
            Operation operation;
            do {
                operation = Util.randomOperationFour();
            }while (!kenKen.generateObjectiveWith(tetromino, operation));

        }
        return generateOperations(new KenKen(kenKen));

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
