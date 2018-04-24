package Util;

import Model.RandomList;

import java.util.Random;

public class Util {
    static Random random=new Random();
    static Operation[] op4=new Operation[]{
            Operation.ADD,
            Operation.DIV,
            Operation.MUL,
            Operation.SUB
    };

    public static RandomList<TetrominoType> tetrominoTypeRandomList() {
        return new RandomList<TetrominoType>(TetrominoType.values());
    }
    public static RandomList<Integer> integerRandomList (int min , int max ){
        Integer[] vals = new Integer[max-min+1];
        for (int i = 0; i < max-min+1; i++)
            vals[i]=min+i;
        return new RandomList<Integer>(vals);
    }

    public static Operation randomOperationFour() {
        return op4[random.nextInt(4)];
    }

    public static RandomList<Operation> operationRandomListFour() {
        return new RandomList<Operation>(new Operation[]{
                Operation.ADD,
                Operation.DIV,
                Operation.MUL,
                Operation.SUB
        });
    }
    /*public RandomList<Byte> byteRandomList (byte min , byte max ){
        Byte[] vals = new Byte[max-min];
        for (Byte i = 0; i < max-min; i++)
            vals[i]=(Byte) min+i;
        return new RandomList<Byte>(vals);
    }*/
}
