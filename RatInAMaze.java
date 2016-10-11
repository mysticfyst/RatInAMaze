/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratinamaze;

/**
 *
 * @author mortred
 */
public class RatInAMaze extends Coordinates{
    
    private final int MAX;
    private Coordinates[] stack;
    private int top=-1;
    private boolean solved;
    
    char[][] maze={{'S','X','X','X','X','X'},
                   {' ',' ',' ',' ',' ','X'},
                   {'X',' ','X','X','X','X'},
                   {'X',' ','X','X','X','X'},
                   {' ',' ',' ','X',' ','G'},
                   {'X','X',' ',' ',' ','X'}};

    public RatInAMaze() {
        this.MAX = 6;
        stack=new Coordinates[MAX*MAX];
        x=0;
        y=0;
    }
    
    public boolean moveRight(){
        System.out.println("TRYING TO MOVE RIGHT");
        if((y+1>=MAX)||((maze[x][y+1]!=' ')&&(maze[x][y+1]!='G')))
            return false;
        y++;
        pushToStack();
        System.out.println("MOVED RIGHT");
        printFinalMaze();
        return true;
    }
    
    public boolean moveLeft(){
        System.out.println("TRYING TO MOVE LEFT");
        if((y-1<0)||((maze[x][y-1]!=' ')&&(maze[x][y-1]!='G')))
            return false;
        y--;
        pushToStack();
        System.out.println("MOVED LEFT");
        printFinalMaze();
        return true;
    }
        
    public boolean moveDown(){
        System.out.println("TRYING TO MOVE DOWN");
        if((x+1>=MAX)||((maze[x+1][y]!=' ')&&(maze[x+1][y]!='G')))
            return false;
        x++;
        pushToStack();
        System.out.println("MOVED DOWN");
        printFinalMaze();
        return true;
    }
    
    public boolean moveUp(){
        System.out.println("TRYING TO MOVE UP");
        if((x-1<0)||((maze[x-1][y]!=' ')&&(maze[x-1][y]!='G')))
            return false;
        x--;
        pushToStack();
        System.out.println("MOVED UP");
        printFinalMaze();
        return true;
    }

    
    private boolean isAtDestination(){
        solved=maze[x][y]=='G';
        return solved;
    }
    
    private void pushToStack(){
        if(maze[x][y]!='G')
            maze[x][y]='O';
        stack[++top]=new Coordinates(x,y);
    }
    
    
    private boolean popFromStack(){
        if(top==0){
            System.out.println("No solution to this maze. Rat starves to death. Or would have if he didn't die of thirst first.");
            return false;
        }
        else{
            System.out.println("BACKTRACK");
            maze[stack[top].x][stack[top].y]='N';
            printFinalMaze();
            top--;
            x=stack[top].x;
            y=stack[top].y;            
            return true;
        }
    }
    
    private void printFinalMaze(){
        System.out.println();
        for(int i =0;i<MAX;i++)
        {
            for(int j=0;j<MAX;j++){
                if((i==x)&&(j==y))
                    System.out.print("R,");
                else
                    System.out.print(maze[i][j]+",");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void moveAroundTheMaze(){
        while(!isAtDestination()){
            if(!moveUp())
                if(!moveRight())
                    if(!moveDown())
                        if(!moveLeft())
                            if(!popFromStack())
                                break;
        }
        if(solved)
            {
                System.out.println("\nSOLVED\n");
                for(int i=0;i<=top;i++)
                {
                    Coordinates c = stack[i];
                    System.out.print("("+c.x+","+c.y+")"+"-");
                }
            }
    }
    

    public static void main(String[] args) {
        RatInAMaze ob = new RatInAMaze();
        ob.printFinalMaze();
        ob.moveAroundTheMaze();
    }
    
}
