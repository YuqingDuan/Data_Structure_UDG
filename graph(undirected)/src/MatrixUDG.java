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

    /**
     * Returns the index of the first adjacent vertex of vertex v and - 1 if it fails.
     *
     * @param v the index of the vertex v
     * @return the index of the first adjacent vertex of vertex v and - 1 if it fails
     */
    private int firstVertex(int v) {
        if (v < 0 || v > (vertexArray.length - 1)) {
            return -1;
        }

        for (int i = 0; i < vertexArray.length; i++) {
            if (adjacencyMatrix[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of vertex v relative to the next adjacent vertex of w, and returns - 1 if it fails.
     *
     * @param v the index of the vertex v
     * @param w the index of the vertex w
     * @return the index of vertex v relative to the next adjacent vertex of w, and returns - 1 if it fails
     */
    private int nextVertex(int v, int w) {
        if (v < 0 || v > (vertexArray.length - 1) || (w < 0) || (w > vertexArray.length - 1)) {
            return -1;
        }

        for (int i = w + 1; i < vertexArray.length; i++) {
            if (adjacencyMatrix[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * depth-first search.
     *
     * @param i       the index of beginning node
     * @param visited if the node has been visited set the flag to true
     */
    private void DFS(int i, boolean[] visited) {
        visited[i] = true;
        System.out.print(vertexArray[i] + " ");
        // traverse all adjacent vertices of the vertex i. if it hasn't visited, keep going.
        for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
            if (!visited[w]) {
                DFS(w, visited);
            }
        }
    }

    public void DFS() {
        boolean[] visited = new boolean[vertexArray.length];

        for (int i = 0; i < vertexArray.length; i++) {
            visited[i] = false;
        }

        System.out.print("DFS: ");
        for (int i = 0; i < vertexArray.length; i++) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    /**
     * breadth-first search.
     */
    public void BFS() {
        int head = 0;
        int rear = 0;
        // auxiliary queue
        int[] queue = new int[vertexArray.length];
        // whether vertex is visited
        boolean[] visited = new boolean[vertexArray.length];

        for (int i = 0; i < vertexArray.length; i++) {
            visited[i] = false;
        }

        System.out.print("BFS: ");
        for (int i = 0; i < vertexArray.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(vertexArray[i] + " ");
                // offer()
                queue[rear++] = i;
            }

            while (head != rear) {
                // poll()
                int j = queue[head++];
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) {
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertexArray[k] + " ");
                        // offer()
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.println();
    }
}
