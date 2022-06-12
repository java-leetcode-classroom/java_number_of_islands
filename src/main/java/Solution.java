import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static private class Pair {
        public int Row;
        public int Col;
        Pair(int row, int col) {
            this.Row = row;
            this.Col = col;
        }
    }
    static Solution.Pair[] directions = new Solution.Pair[]{
            new Solution.Pair(1,0),
            new Solution.Pair(-1,0),
            new Solution.Pair(0, 1),
            new Solution.Pair(0, -1)
    };
    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int islands = 0;
        int ROW = grid.length;
        int COL = grid[0].length;
        boolean[][] visited = new boolean[ROW][COL];
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                visited[row][col] = false;
            }
        }
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (grid[row][col]=='1' && !visited[row][col]) {
                    bfs(row, col, grid, visited);
                    islands++;
                }
            }
        }
        return islands;
    }
    private void bfs(int row, int col, char[][] grid, boolean[][] visited) {
        Queue<Solution.Pair> queue = new LinkedList<>();
        int ROW = grid.length;
        int COL = grid[0].length;
        queue.add(new Solution.Pair(row, col));
        visited[row][col] = true;
        while(queue.size() > 0) {
            Pair top = queue.poll();
            for (Pair direction : directions) {
                int shiftRow = top.Row + direction.Row;
                int shiftCol = top.Col + direction.Col;
                if (shiftRow < 0 || shiftRow >= ROW || shiftCol < 0 || shiftCol >= COL ||
                        visited[shiftRow][shiftCol] || grid[shiftRow][shiftCol] == '0'
                ) {
                    continue;
                }
                visited[shiftRow][shiftCol] = true;
                queue.add(new Solution.Pair(shiftRow, shiftCol));
            }
        }
    }
}
