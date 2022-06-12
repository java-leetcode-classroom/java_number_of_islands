# java_number_of_islands

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return *the number of islands*.

An **island** is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

## Examples

**Example 1:**

```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

```

**Example 2:**

```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

```

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`.

## 解析

給定一個 2 D 二元矩陣 grid ， 每個元素 grid[r][c] 只有 1 或是 0 兩種值

1 代表是陸地，0 代表是 水

如果水平或是垂直相鄰位置都是 1 代表 cell屬於同一個 island

題目實作一個演算法從 grid 找出總共有幾個 island

直覺去思考，可以發現要找出有幾個 island 代表要去找有幾個相連的區塊

而要找出相連的區塊會透過 BFS 從該區塊相鄰為 1 的 cell 開始找尋直到遇到 0 或是 邊界

如下圖

![](https://i.imgur.com/XYBldEo.png)

會發現需要紀錄每個拜訪過的 grid，可以使用 hashSet

然後逐步每個 cell 檢查

## 程式碼

```java
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

```
## 困難點

1. 需要理解找出連結在一起 cell 要使用 BFS
2. 需要理解 BFS 實作

## Solve Point

- [x]  Understand what problem to solve
- [x]  Analysis Complexity