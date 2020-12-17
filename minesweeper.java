package project;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//needs to be done
//This is implementation of different version of Minesweeper without GUI
//1) Count nearest mines
//2) when box is empty open all nearest empty boxes and also nearest boxes with numbers
//3) first hit not a mine?
//4) re-implement putting mines when clicked
//5) finish openAdjacentEmpty method

public class minesweeper {
    static int[][]  arr;
    public static void main(String[] args) {
        int height = 10;
        int width = 10;
        int minesNeeded = 10;
        arr = new int[height][width];
        int mines = 0;
        while(mines<minesNeeded){
            Random random = new Random();
            int h = random.nextInt(height);
            int w = random.nextInt(width);
            if(arr[h][w]>=0){
                arr[h][w] = -1;
                mines++;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int count = 0;
                if(arr[i][j]!=-1){
                    arr[i][j] = mineFinder(i,j);
                }
            }
        }
        //arr of mines
//        for (int i = 0; i < height; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }


        String[][] arrGame = plot(height,width);
        game(arrGame,height,width,mines);

    }
    public static boolean isValid(int i, int j){
        return (i >= 0) && (i < arr.length) &&
                (j >= 0) && (j < arr[0].length);
    }
    public static void openAdjacentEmpty(String[][] arrGame,int  i,int  j){
                //North
                //South
                //West
                //East
                //North-West
                //North-East
                //South-West
                //South-East

    }
    public static int mineFinder(int i, int j){
        //need to develop algorithm for finding near mines
        //finished, but it looks redundant
        int ans = 0;
        if(i-1>=0){
            if(arr[i-1][j]==-1){
                ans++;
            }
        }
        if(j-1>=0){
            if(arr[i][j-1]==-1){
                ans++;
            }
        }
        if(i-1>=0 &&j-1>=0){
            if(arr[i-1][j-1]==-1){
                ans++;
            }
        }
        if(i+1<= arr.length-1){
            if(arr[i+1][j]==-1){
                ans++;
            }
        }
        if(j+1<= arr[i].length-1){
            if(arr[i][j+1]==-1){
                ans++;
            }
        }
        if(j+1< arr[i].length && i+1< arr.length){
            if(arr[i+1][j+1]==-1){
                ans++;
            }
        }
        if(i-1>=0 && j+1<arr[i].length){
            if(arr[i-1][j+1]==-1){
                ans++;
            }
        }
        if(i+1<arr.length && j-1>=0){
            if(arr[i+1][j-1]==-1){
                ans++;
            }
        }
        return ans;
    }
    public static String[][] plot(
            int height, int width){
        String[][] arrGame = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                arrGame[i][j] = "▢";
            }
        }

        return arrGame;
    }
    public static void  printGame(String[][] arrGame){
        int height = arrGame.length;
        int width = arrGame[0].length;
                for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(arrGame[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static  void game(String[][] arrGame, int height, int width,int mines){
        printGame(arrGame);
        long startTime = System.nanoTime();

        Scanner scanner = new Scanner(System.in);
        int opening = 0;
        while(opening<(width*height-mines)){
            String word = scanner.nextLine();
            String[] word1 = word.split(" ");
            int x = Integer.parseInt(word1[0]);
            int y = Integer.parseInt(word1[1]);
            if(arr[x][y]==-1){
                System.out.println("You Lost");
                //need to show where are the bombs
                //arr of mines
            for (int i = 0; i < height; i++) {
                System.out.println(Arrays.toString(arr[i]));
            }


                System.exit(0);
            }
            else{
                arrGame[x][y] = Integer.toString(arr[x][y]);
                printGame(arrGame);
                opening++;
            }
        }
        System.out.println("Congratulations!");
        long stopTime = System.nanoTime();
        System.out.print("It took for you  ");
        System.out.println((stopTime - startTime)/1_000_000_000 +" seconds");

    }
}
