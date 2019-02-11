public class TestMatrixUDG {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = {
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};

        MatrixUDG graph = new MatrixUDG(vertexes, edges);

        graph.print();
    }
}
