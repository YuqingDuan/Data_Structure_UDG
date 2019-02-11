/**
 * Adjacent matrix undirected graph.
 *
 * @author Yuqing Duan
 * @version 1.0
 * @data 2019/02/10
 * @reference http://www.cnblogs.com/skywang12345/p/3707604.html
 */
public class MatrixUDG {
    // vertex array
    private char[] vertexArray;
    // adjacency matrix
    private int[][] adjacencyMatrix;

    /**
     * Constructor with parameters.
     * Uses adjacent matrix to create undirected graph.
     *
     * @param vertexes vertex array
     * @param edges    edge array
     */
    public MatrixUDG(char[] vertexes, char[][] edges) {
        // initialize vertex array and adjacency matrix
        this.vertexArray = new char[vertexes.length];
        this.adjacencyMatrix = new int[vertexes.length][vertexes.length];

        for (int i = 0; i < vertexArray.length; i++) {
            this.vertexArray[i] = vertexes[i];
        }

        // get the start vertexes(index) and end vertexes(index) of all the edges in the graph
        for (int i = 0; i < edges.length; i++) {
            int startIndex = getIndex(edges[i][0]);
            int endIndex = getIndex(edges[i][1]);

            // build adjacency matrix
            this.adjacencyMatrix[startIndex][endIndex] = 1;
            this.adjacencyMatrix[endIndex][startIndex] = 1;
        }
    }

    /**
     * Gets the index of "ch".
     *
     * @param ch start vertex or end vertex
     * @return the index of "ch"
     */
    private int getIndex(char ch) {
        for (int i = 0; i < vertexArray.length; i++) {
            if (vertexArray[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints undirected graph.
     */
    public void print() {
        System.out.println("Matrix Graph: ");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                System.out.print(adjacencyMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
