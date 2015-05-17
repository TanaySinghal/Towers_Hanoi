import java.util.Scanner;

/**
 * Created by tanaysinghal on 4/30/15.
 */
public class Towers_Hanoi {
    public static void main(String args[]) {
        new Towers_Hanoi();
    }

    int [] A1, A2, A3;

    Towers_Hanoi() {
        System.out.print("Please enter the amount of numbers in the starting tower (1 to 12): ");
        int n = new Scanner(System.in).nextInt();

        A1 = new int[n];
        A2 = new int[n];
        A3 = new int[n];

        //Fill first array completely
        for(int i = n-1; i >= 0; i --) {
            A1[i] = i+1;
        }

        //Show initial arrays
        System.out.println("Solving a " + A1.length + " x " + A1.length + " tower of hanoi puzzle ...");
        printIt();

        int count = 0;
        while(true) {
            moveBetween(A1, A3);
            count ++;
            if(Solved(A1, A2) || Solved(A1, A3)) break;

            moveBetween(A1, A2);
            count ++;
            if(Solved(A1, A2)  || Solved(A1, A3)) break;

            moveBetween(A2, A3);
            count ++;
            if(Solved(A1, A2) || Solved(A1, A3)) break;
        }
        System.out.println("\n\nIt took " + (count) + " steps.");
    }

    void moveBetween(int [] A, int [] B) {
        //if top number in A1 is 0 then we can must move from A3 to A1
        if (numInA(A) == 0) {
            moveBetweenAandB(B, A);
        } else {
            moveBetweenAandB(A, B);
        }
    }
    //Decides which number from which array should move.
    void moveBetweenAandB(int [] A1, int[] A2) {
        int numInA1 = numInA(A1);
        int numInA2 = numInA(A2);
        if(numInA2 > numInA1 || numInA2 == 0) {
            moveFromAToB(A1, A2);
        }
        else if(numInA1 > numInA2 || numInA1 == 0) {
            moveFromAToB(A2, A1);
        }
        printIt();
    }

    //Prints array for visualization
    void printIt() {
        System.out.println();

        for(int i = 0; i < A1.length; i ++) {
            System.out.println();
            for(int j = 0; j < 3; j ++) {
                switch(j) {
                    case 0:
                        System.out.printf("%-3s", A1[i]);
                        break;
                    case 1:
                        System.out.printf("%-3s", A2[i]);
                        break;
                    case 2:
                        System.out.printf("%-3s", A3[i]);
                        break;
                } //end switch
            } //end inner for loop
        } //end outer for loop
    }

    void moveFromAToB(int [] A1, int [] A2) {
        int numToMove = 0;

        //Delete number from first array
        for(int i = 0; i < A1.length; i ++) {
            if(A1[i] != 0) {
                //figure out what numToMove is
                numToMove = A1[i];
                A1[i] = 0;
                break;
            }
        }

        //Add it to second array
        for(int i = 0; i < A2.length; i ++) {
            if (i == A2.length -1 || A2[i+1] != 0) {
                A2[i] = numToMove;
                break;
            }
        }
    }

    int numInA(int[] Array) {
        int numInA = 0;
        for(int i = Array.length - 1; i > 0; i --) {
            if(Array[i] != 0) {
                numInA = Array[i];
            }
        }
        return numInA;
    }

    boolean Solved (int[] A1, int[] A2) {
        int length = A1.length-1;
        return (A1[length] == 0 && A2[length] == 0);
    }

}
