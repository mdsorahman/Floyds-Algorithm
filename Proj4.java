// Md Rahman
// CS 4050 - Algorithms
// Implementing Floyd's algorithm

import java.util.Scanner;
public class Proj4 {

    private static int[][] dist;     // hold weight for each row and col
    private static int[][] interVer; // hold intermediate vertex for each rol and col
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of vertices: ");
        int vertices = input.nextInt();
        interVer = new int[vertices][vertices];
        dist = new int[vertices][vertices];

        // Gather user input for weights
        for(int i = 0; i < dist.length; i++) {
            for(int j = 0; j < dist.length; j++) {
                System.out.println("Enter weight for vertex " + (i+1) + " to vertex " + (j+1));
                int weight = input.nextInt();
                dist[i][j] = weight;
            } // end inner for
        } // end outer for


        int inter = 0;
        displayTables(inter); // print out initial table D0 (no values changed)

        // compute new weights using vertex 1 - n
        for(inter = 0; inter < vertices; inter++) {
            computeTables(inter);
        }
    } // end main

    // compute appropriate weights using given intermediate vertex, and store in appropriate arrays
    private static void computeTables(int inter) {
        for(int i = 0; i < dist.length; i++) {

            // only compute outer for loop if row i is not the intermediate vertex
            if (i != inter) {

                for (int j = 0; j < dist.length; j++) {

                    // only compute inner for loop if col j is not the intermediate vertex
                    if(j != inter) {

                        if(dist[i][inter] != -1 && dist[inter][j] != -1) {
                            // if no edge between vertices
                            if (dist[i][j] == -1) {
                                dist[i][j] = dist[i][inter] + dist[inter][j];
                                interVer[i][j] = inter + 1; // plus 1 so user doesn't see intermediate of 0
                            }
                            else if(dist[i][inter] + dist[inter][j] < dist[i][j]) {
                                dist[i][j] = dist[i][inter] + dist[inter][j];
                                interVer[i][j] = inter + 1;
                            }
                        }
                    }
                } // end inner for
            }
        } // end outer for

        displayTables(inter + 1 );
    } // end computeTables

    // displays appropriate tables D0 ~ Dn (called from computeTables method)
    private static void displayTables(int inter){
        System.out.println("\nD" + inter);
        System.out.println("    1       2       3       4       5");
        System.out.println("    --------------------------------- ");
        int columnLabel = 1;
        for(int i = 0; i < dist.length; i++){
            System.out.print(columnLabel + " |  ");
            for(int j = 0; j < dist.length; j++) {
                System.out.print("  " + dist[i][j] + "   ");
            }
            System.out.print("\n  | ");
            for(int k = 0; k < dist.length; k++) {
                System.out.print("     " + interVer[i][k]);
            }
            System.out.println("\n");
            columnLabel++;
        } // end outer for
    } // end displayTables

} // end class
