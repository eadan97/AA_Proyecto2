package Util;

import Model.RandomList;

public class UsualRandomList {
    public static RandomList<TetrominoType> tetrominoTypeRandomList() {
        return new RandomList<TetrominoType>(TetrominoType.values());
    }
    public static RandomList<Integer> integerRandomList (int min , int max ){
        Integer[] vals = new Integer[max-min+1];
        for (int i = 0; i < max-min+1; i++)
            vals[i]=min+i;
        return new RandomList<Integer>(vals);
    }
    /*public RandomList<Byte> byteRandomList (byte min , byte max ){
        Byte[] vals = new Byte[max-min];
        for (Byte i = 0; i < max-min; i++)
            vals[i]=(Byte) min+i;
        return new RandomList<Byte>(vals);
    }*/
}
