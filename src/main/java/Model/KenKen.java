package Model;


import Util.TetrominoType;

import java.util.ArrayList;
import java.util.Arrays;

public class KenKen {
    byte [][] currentData;
    public byte [][] latinSquare;
    short[][] tetrominosMatrix;
    ArrayList<Tetromino> tetrominos;
    short tetrominosInBoard;
    public short size;


    public KenKen(byte size) {
        this.size = size;
        this.currentData=new byte[size][size];
        this.latinSquare=new byte[size][size];
        for (byte[] row : this.latinSquare)
            Arrays.fill(row, (byte) -1);
        this.tetrominosMatrix =new short[size][size];
        this.tetrominosInBoard =0;
        this.tetrominos=new ArrayList<Tetromino>();
    }

    public KenKen(KenKen kenKen) {
        this.size = kenKen.size;
        this.currentData=new byte[size][size];
        this.tetrominosMatrix =new short[size][size];
        this.latinSquare=new byte[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++) {
                this.currentData[i][j] = kenKen.currentData[i][j];
                this.tetrominosMatrix[i][j] = kenKen.tetrominosMatrix[i][j];
                this.latinSquare[i][j]=kenKen.latinSquare[i][j];
            }
        this.tetrominosInBoard =kenKen.tetrominosInBoard;
        this.tetrominos=new ArrayList<Tetromino>(kenKen.tetrominos);
    }

    public void generateShapes(){

    }

    public boolean placeTetromino(byte x, byte y, TetrominoType tetromino){
        if (tetrominosMatrix[x][y]==0){
                    if (tetromino==TetrominoType.DOT_0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y)
                        }));
                        return true;
                    }
                    //..
                    else if(tetromino==TetrominoType.DOTDOT_0&&y!=size-1&& tetrominosMatrix[x][y+1]==0) {
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, (byte) (y+1))
                        }));
                        return true;
                    }else if(tetromino==TetrominoType.DOTDOT_1&&x!=size-1&& tetrominosMatrix[x+1][y]==0) { //:
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate((byte) (x+1),y)
                        }));
                        return true;
                    }
                    //..
                    else if(tetromino==TetrominoType.I_0&&y<size-3&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x][y+2]==0&&
                            tetrominosMatrix[x][y+3]==0) {
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x][y+2]= tetrominosInBoard;
                        tetrominosMatrix[x][y+3]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, (y+1)),
                                new Coordinate(x, (y+2)),
                                new Coordinate(x, (y+3))
                        }));
                        return true;
                    }else if(tetromino==TetrominoType.I_1&&x<size-3&&
                            tetrominosMatrix[x+1][y]==0&&
                            tetrominosMatrix[x+2][y]==0&&
                            tetrominosMatrix[x+3][y]==0) { //:
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y]= tetrominosInBoard;
                        tetrominosMatrix[x+3][y]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate((x+1), y),
                                new Coordinate((x+2), y),
                                new Coordinate((x+3), y)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.O_0&&x<size-1&&y<size-1&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x+1][y]==0&&
                            tetrominosMatrix[x+1][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x, y+1),
                                new Coordinate( x+1,  y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.J_0&&y>0&&x<size-2&&
                            tetrominosMatrix[x+1][y]==0&&
                            tetrominosMatrix[x+2][y]==0&&
                            tetrominosMatrix[x+2][y-1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y-1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+2, y),
                                new Coordinate(x+2, y-1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.J_1&&x<size-1&&y<size-2
                            && tetrominosMatrix[x+1][y]==0
                            && tetrominosMatrix[x+1][y+1]==0
                            && tetrominosMatrix[x+1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+1, y+1),
                                new Coordinate(x+1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.J_2&&y>0&&x<size-2&&
                            tetrominosMatrix[x][y-1]==0&&
                            tetrominosMatrix[x+1][y-1]==0&&
                            tetrominosMatrix[x+2][y-1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y-1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y-1]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y-1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y-1),
                                new Coordinate(x+1, y-1),
                                new Coordinate(x+2, y-1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.J_3&&x<size-1&&y<size-2
                            && tetrominosMatrix[x][y+1]==0
                            && tetrominosMatrix[x][y+2]==0
                            && tetrominosMatrix[x+1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x][y+2]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x, y+2),
                                new Coordinate(x+1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.L_0&&y<size-1&&x<size-2&&
                            tetrominosMatrix[x+1][y]==0&&
                            tetrominosMatrix[x+2][y]==0&&
                            tetrominosMatrix[x+2][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+2, y),
                                new Coordinate(x+2, y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.L_1&&x>0&&y<size-2
                            && tetrominosMatrix[x-1][y]==0
                            && tetrominosMatrix[x-1][y+1]==0
                            && tetrominosMatrix[x-1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x-1][y]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x-1, y),
                                new Coordinate(x-1, y+1),
                                new Coordinate(x-1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.L_2&&y<size-2&&x<size-1&&
                            tetrominosMatrix[x+1][y]==0&&
                            tetrominosMatrix[x+1][y+1]==0&&
                            tetrominosMatrix[x+1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+1, y+1),
                                new Coordinate(x+1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.L_3&&x>0&&y<size-2
                            && tetrominosMatrix[x][y+1]==0
                            && tetrominosMatrix[x][y+2]==0
                            && tetrominosMatrix[x-1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x][y+2]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x, y+2),
                                new Coordinate(x-1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.T_0&&y<size-2&&x>0&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x][y+2]==0&&
                            tetrominosMatrix[x-1][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x][y+2]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x, y+2),
                                new Coordinate(x-1, y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.T_1&&x<size-2&&y>0
                            && tetrominosMatrix[x+1][y]==0
                            && tetrominosMatrix[x+2][y]==0
                            && tetrominosMatrix[x+1][y-1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y-1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+2, y),
                                new Coordinate(x+1, y-1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.T_2&&y<size-2&&x<size-1&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x][y+2]==0&&
                            tetrominosMatrix[x+1][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x][y+2]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x, y+2),
                                new Coordinate(x+1, y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.T_3&&x<size-2&&y<size-1
                            && tetrominosMatrix[x+1][y]==0
                            && tetrominosMatrix[x+2][y]==0
                            && tetrominosMatrix[x+1][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+2, y),
                                new Coordinate(x+1, y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.S_0&&y<size-2&&x<size-1&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x+1][y+1]==0&&
                            tetrominosMatrix[x+1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x+1, y+1),
                                new Coordinate(x+1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.S_1&&x<size-2&&y<size-1
                            && tetrominosMatrix[x+1][y]==0
                            && tetrominosMatrix[x+1][y+1]==0
                            && tetrominosMatrix[x+2][y+1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y+1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+1, y+1),
                                new Coordinate(x+2, y+1)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.Z_0&&y<size-2&&x>0&&
                            tetrominosMatrix[x][y+1]==0&&
                            tetrominosMatrix[x-1][y+1]==0&&
                            tetrominosMatrix[x-1][y+2]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+1]= tetrominosInBoard;
                        tetrominosMatrix[x-1][y+2]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x, y+1),
                                new Coordinate(x-1, y+1),
                                new Coordinate(x-1, y+2)
                        }));
                        return true;
                    }
                    else if(tetromino==TetrominoType.Z_1&&x<size-2&&y>0
                            && tetrominosMatrix[x+1][y]==0
                            && tetrominosMatrix[x+1][y-1]==0
                            && tetrominosMatrix[x+2][y-1]==0){
                        tetrominosMatrix[x][y]=++tetrominosInBoard;
                        tetrominosMatrix[x+1][y]= tetrominosInBoard;
                        tetrominosMatrix[x+1][y-1]= tetrominosInBoard;
                        tetrominosMatrix[x+2][y-1]= tetrominosInBoard;
                        tetrominos.add(new Tetromino(tetromino,new Coordinate[]{
                                new Coordinate(x, y),
                                new Coordinate(x+1, y),
                                new Coordinate(x+1, y-1),
                                new Coordinate(x+2, y-1)
                        }));
                        return true;
                    }

            }
            return false;
    }

    public void unplaceLast(){
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (tetrominosMatrix[i][j]== tetrominosInBoard)
                    tetrominosMatrix[i][j]=0;
        tetrominosInBoard--;
    }

    public Coordinate isTetromined() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (tetrominosMatrix[i][j]==0)
                    return new Coordinate(i,j);
        return null;
    }

    public void printTetrominoes() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tetrominosMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean isSamePentominoArray(KenKen obj) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (this.tetrominosMatrix[i][j]!=obj.tetrominosMatrix[i][j])
                    return false;
        return true;
    }

    public boolean placeNumber(byte i, byte j, Integer integer) {
        for (int k = 0; k < size; k++) {
            if(latinSquare[i][k]==integer.byteValue()||latinSquare[k][j]==integer.byteValue())
                return false;
        }
        latinSquare[i][j]=integer.byteValue();
        return true;
    }

    public Coordinate isLatinSquared() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (latinSquare[i][j]==(byte)-1)
                    return new Coordinate(i,j);
        return null;
    }
}
