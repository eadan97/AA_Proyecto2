package Model;


import Util.Operation;
import Util.TetrominoType;

import java.util.ArrayList;
import java.util.Arrays;

public class KenKen {
    public byte [][] currentData;
    public byte [][] latinSquare;
    public short[][] tetrominosMatrix;
    public ArrayList<Tetromino> tetrominos;
    short tetrominosInBoard;
    public short size;
    private Coordinate lastMove;


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

    public boolean placeNumberInLatinSquare(byte i, byte j, Integer integer) {
        for (int k = 0; k < size; k++) {
            if(latinSquare[i][k]==integer.byteValue()||latinSquare[k][j]==integer.byteValue())
                return false;
        }
        latinSquare[i][j]=integer.byteValue();
        return true;
    }
    public boolean placeNumberInSolution(Tetromino tetromino, Integer integer) {
        int objectiveVal=tetromino.operation==Operation.MUL?1:0;
        for (Coordinate coordinate :
                tetromino.positions) {
            //PODAS
            if (currentData[coordinate.x][coordinate.y]==-1) {
                if(coordinate==tetromino.positions[tetromino.positions.length-1])
                    switch (tetromino.operation){
                        case ADD:
                            if(objectiveVal+integer!=tetromino.objective)
                                return false;
                            break;
                        case SUB:
                            if(objectiveVal-integer!=tetromino.objective)
                                return false;
                            break;
                        case DIV:
                            if(integer!=0&&objectiveVal/integer!=tetromino.objective)
                                return false;
                            break;
                        case MUL:
                            if(objectiveVal*integer!=tetromino.objective)
                                return false;
                            break;
                        case MOD:
                            if(integer!=0&&objectiveVal%integer!=tetromino.objective)
                                return false;
                            break;
                        case EXP:
                            if(Math.pow(integer,3)!=tetromino.objective)
                                return false;
                            break;
                    }
                    else {
                    switch (tetromino.operation){
                        case ADD:
                            if(objectiveVal+integer>tetromino.objective)
                                return false;
                            break;
                        case SUB:
                            if ((coordinate==tetromino.positions[0]&&
                                    integer<=tetromino.objective)||
                                    objectiveVal-integer<tetromino.objective)
                                return false;
                            break;
                        case DIV:
                            if ((coordinate==tetromino.positions[0]&&
                                    integer<tetromino.objective)||
                                    objectiveVal/integer<tetromino.objective)
                                return false;
                            break;
                        case MUL:
                            objectiveVal*=integer;
                            if(objectiveVal>tetromino.objective)
                                return false;
                            break;
                    }

                }
                for (int k = 0; k < size; k++) {
                    if(currentData[k][coordinate.y]==integer.byteValue()||
                            currentData[coordinate.x][k]==integer.byteValue())
                        return false;
                }
                currentData[coordinate.x][coordinate.y]=integer.byteValue();
                lastMove=coordinate;
                return true;
            }else {
                switch (tetromino.operation){
                    case ADD:
                        objectiveVal+=currentData[coordinate.x][coordinate.y];
                        break;
                    case SUB:
                        if(coordinate==tetromino.positions[0])
                            objectiveVal=currentData[coordinate.x][coordinate.y];
                        else
                            objectiveVal-=currentData[coordinate.x][coordinate.y];
                        break;
                    case DIV:
                        if(coordinate==tetromino.positions[0])
                            objectiveVal=currentData[coordinate.x][coordinate.y];
                        else
                            objectiveVal/=currentData[coordinate.x][coordinate.y];
                        break;
                    case MUL:
                        objectiveVal*=currentData[coordinate.x][coordinate.y];
                        break;
                    case MOD:
                        objectiveVal=currentData[coordinate.x][coordinate.y];
                        break;
                }
            }

        }

        return false;
    }

    public Coordinate isLatinSquared() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (latinSquare[i][j]==(byte)-1)
                    return new Coordinate(i,j);
        return null;
    }

    public Tetromino isFullWithOperations() {
        for (Tetromino tetromino :
                tetrominos) {
            if (tetromino.operation==null)
                return tetromino;
        }
        return null;
    }

    public boolean generateObjectiveWith(Tetromino tetromino, Operation op) {
        tetromino.operation=op;
        switch (op){
            case EXP:
                Coordinate coor;
                coor = tetromino.positions[0];
                tetromino.objective= (int) Math.pow(latinSquare[coor.x][coor.y],3);
                break;
            case ADD:
                for (Coordinate coordinate:
                        tetromino.positions) {
                    tetromino.objective+=latinSquare[coordinate.x][coordinate.y];
                }
                break;
            case SUB:

                for (Coordinate coordinate:
                        tetromino.positions) {
                    if(coordinate==tetromino.positions[0])
                        tetromino.objective=latinSquare[coordinate.x][coordinate.y];
                    else
                        tetromino.objective-=latinSquare[coordinate.x][coordinate.y];
                }
                break;
            case DIV:
                for (Coordinate coordinate :
                        tetromino.positions) {
                    if (latinSquare[coordinate.x][coordinate.y]==0)
                        return false;
                }

                for (Coordinate coordinate:
                        tetromino.positions) {
                    if(coordinate==tetromino.positions[0])
                        tetromino.objective=latinSquare[coordinate.x][coordinate.y];
                    else
                        tetromino.objective/=latinSquare[coordinate.x][coordinate.y];
                }
                if (tetromino.objective==0)
                    return false;
                break;
            case MUL:
                tetromino.objective=1;
                for (Coordinate coordinate:
                        tetromino.positions) {
                    tetromino.objective*=latinSquare[coordinate.x][coordinate.y];
                }
                break;
            case MOD:
                Coordinate coordinate1=tetromino.positions[0];
                Coordinate coordinate2=tetromino.positions[1];
                if (latinSquare[coordinate2.x][coordinate2.y]==0)
                    return false;
                tetromino.objective=latinSquare[coordinate1.x][coordinate1.y]%latinSquare[coordinate2.x][coordinate2.y];
                break;



        }
        return true;
    }

    public void clearCurrentData() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentData[i][j]=-1;
            }
        }
    }

    public Tetromino isSolved() {
        for (Tetromino tetromino :
                tetrominos) {
            for (Coordinate coordinate :
                    tetromino.positions) {
                if (currentData[coordinate.x][coordinate.y]==(byte)-1)
                    return tetromino;
            }
        }
        return null;
        /*for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (currentData[i][j]==(byte)-1)
                    return new Coordinate(i,j);
        return null;*/
    }

    public void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(currentData[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void removeLastMove() {
        currentData[lastMove.x][lastMove.y]=-1;
    }

    public void printSquare() {
        //System.out.println("- Printing Latin Square -");
        for (int i = 0; i < this.latinSquare.length ; i++)
        {
            for (int j = 0 ; j < latinSquare[0].length ; j++)
            {
                System.out.print(latinSquare[i][j]);
            }
            System.out.println();
        }
    }

    public void printCurrentData() {
        //System.out.println("- Printing Latin Square -");
        for (int i = 0; i < this.currentData.length ; i++)
        {
            for (int j = 0 ; j < currentData[0].length ; j++)
            {
                System.out.print(currentData[i][j]);
            }
            System.out.println();
        }
    }


}
