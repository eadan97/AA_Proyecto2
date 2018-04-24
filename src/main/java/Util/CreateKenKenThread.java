//package Util;

import Model.KenKen;
import Model.RandomList;

import java.util.concurrent.BlockingQueue;

//public class CreateKenKenThread implements Runnable {
    /*private final BlockingQueue queue;
    public boolean endedBacktracking=false;
    private byte size;
    public CreateKenKenThread(BlockingQueue queue, byte size) {
        this.queue = queue;
        this.size=size;
    }

    public void run() {
        KenKen kenKen = new KenKen(size);
        //findTetrominos(kenKen, Util.tetrominoTypeRandomList());
            for (byte i = 0; i < size; i++) {
                for (byte j = 0; j < size; j++) {
                    shape:
                    for (TetrominoType tetrominoType :
                            Util.tetrominoTypeRandomList()) {
                            if (kenKen.placeTetromino(i, j, tetrominoType)) {
                                //if (kenKen.isTetromined()) {
                                    kenKen.printTetrominoes();
                                    //return (kenKen);
                                //}
                                break shape;
                            }
                    }
                }
            }


            /*do {
               //placeTetromino shape
            }while (!kenKen.isTetromined());*/
            ////Find Tetrominos
            ////Find Latin Square
            ////Find operation per tetromino

    //}

    /*boolean findTetrominos(KenKen kenKen, RandomList<TetrominoType> tetrominoTypes){
        if (kenKen.isTetromined()){
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //kenKen.printTetrominoes();
            //generar numeros
            queue.add(new KenKen(kenKen));
            System.out.println(queue.size());
            System.out.println("!!!!!!!!!!!!!!!!!!!"+((KenKen) queue.toArray()[0]).isSamePentominoArray(((KenKen)queue.toArray()[queue.size()-1]))+"!!!!!!!!!!!!!!!!!!!!!!");
            return true;
        }
        //Iterator<TetrominoType> iterator = tetrominoTypes.iterator();
        TetrominoType type = tetrominoTypes.pop();
        while (type!=null){
                for (byte i = 0; i < size; i++) {
                    for (byte j = 0; j < size; j++) {

                        if (kenKen.placeTetromino(i,j,type)){
                            System.out.println("------------------------------------------------");
                            kenKen.printTetrominoes();
                            findTetrominos(new KenKen(kenKen), Util.tetrominoTypeRandomList());
                            kenKen.unplaceLast();
                            //tetrominoTypes.add(type);
                            //integers.add(rotation);
                        }
                    }
            }
            type=tetrominoTypes.pop();
        }
        return false;

    }*/

    /*synchronized void generateKenKen(){
        notify();
    }
}*/
