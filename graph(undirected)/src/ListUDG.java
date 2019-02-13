/**
 * Adjacent linked list undirected graph.
 *
 * @author Yuqing Duan
 * @version 1.0
 * @data 2019/02/10
 * @reference http://www.cnblogs.com/skywang12345/p/3707612.html
 */
public class ListUDG {
    /**
     * ENode is the structure corresponding to the nodes of the linked list contained in the vertices of the adjacent list.
     */
    private static final class ENode {
        // the index of the vertex corresponding to the node in array
        int adjvex;
        // a reference to the next edge
        ENode next;
    }

    /**
     * VNode is the structure corresponding to the vertices of adjacent array.
     */
    private static final class VNode {
        // vertexes
        char vertex;
        // first edge
        ENode firstEdge;
    }

    // vertexes array
    private VNode[] vertexArray;

    /**
     * Constructor with parameters.
     * Uses adjacent linked list to create undirected graph.
     *
     * @param vertexes vertex array
     * @param edges    edge array
     */
    public ListUDG(char[] vertexes, char[][] edges) {
        // initialize vertex array
        this.vertexArray = new VNode[vertexes.length];
        for (int i = 0; i < vertexArray.length; i++) {
            this.vertexArray[i] = new VNode();
            this.vertexArray[i].vertex = vertexes[i];
            this.vertexArray[i].firstEdge = null;
        }

        // initialize adjacency linked list
        for (int i = 0; i < edges.length; i++) {
            // get the start vertexes(index) and end vertexes(index) of all the edges in the graph
            int startIndex = getIndex(edges[i][0]);
            int endIndex = getIndex(edges[i][1]);

            ENode node1 = new ENode();
            node1.adjvex = endIndex;
            if (vertexArray[startIndex].firstEdge == null) {
                vertexArray[startIndex].firstEdge = node1;
            } else {
                linkLast(vertexArray[startIndex].firstEdge, node1);
            }

            ENode node2 = new ENode();
            node2.adjvex = startIndex;
            if (vertexArray[endIndex].firstEdge == null) {
                vertexArray[endIndex].firstEdge = node2;
            } else {
                linkLast(vertexArray[endIndex].firstEdge, node2);
            }
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
            if (vertexArray[i].vertex == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Links the node to the end of the list.
     *
     * @param list first node of a list
     * @param node node need to be appended
     */
    private void linkLast(ENode list, ENode node) {
        while (list.next != null) {
            list = list.next;
        }
        list.next = node;
    }

    /**
     * Prints undirected graph.
     */
    public void print() {
        System.out.println("List Graph: ");
        for (int i = 0; i < vertexArray.length; i++) {
            System.out.print(i + ":" + vertexArray[i].vertex);
            ENode node = vertexArray[i].firstEdge;
            while (node != null) {
                System.out.print("---> " + node.adjvex + "(" + vertexArray[node.adjvex].vertex + ")");
                node = node.next;
            }
            System.out.println();
        }
    }

    /**
     * depth-first search.
     *
     * @param i       the index of beginning node
     * @param visited if the node has been visited set the flag to true
     */
    private void DFS(int i, boolean[] visited) {
        ENode node;

        visited[i] = true;
        System.out.print(vertexArray[i].vertex + " ");
        node = vertexArray[i].firstEdge;
        while (node != null) {
            if (!visited[node.adjvex]) {
                DFS(node.adjvex, visited);
            }
            node = node.next;
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
                System.out.print(vertexArray[i].vertex + " ");
                // offer()
                queue[rear++] = i;
            }

            while (head != rear) {
                // poll()
                int j = queue[head++];
                ENode node = vertexArray[j].firstEdge;
                while (node != null) {
                    int k = node.adjvex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertexArray[k].vertex + " ");
                        queue[rear++] = k;
                    }
                    node = node.next;
                }
            }
        }
        System.out.println();
    }
}
