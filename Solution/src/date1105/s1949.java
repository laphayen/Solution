package date1105;

import java.util.Arrays;
import java.util.Scanner;

public class s1949 {

    // 리턴 조건이 어렵다.
    
    static int n;
    static int k;
    static int [][] map;
    
    // 지도니깐 방문했던 곳을 다시 방문하는 탐색x
    static boolean [][] v;
    
    // 최대값 - 시작 위치를 잡을곳
    static int m;
    static int result;
    
    static int [] dr = {0, 0, -1, 1};
    static int [] dc = {-1, 1, 0, 0};
    
    public static void main(String[] args) {
        
        System.setIn(s1949.class.getResourceAsStream("s1949.txt"));
        Scanner sc = new Scanner(System.in);
        
        // 테스트를 돌려
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            
            n = sc.nextInt();
            k = sc.nextInt();
            
            m = 0;
            result = 0;
            
            
            map = new int [n][n];
            v = new boolean [n][n];
            
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    map[r][c] = sc.nextInt();
                    if (m < map[r][c]) {
                        m = map[r][c];
                    }
                }
            }
//            System.out.println(m);
            
            /*
             * arraylist를 안 돌려도 되겠다.
             * 어차피 각 꼭지점마다 dfs 시작점으로 돌리니깐/
             */
            
            for (int r= 0; r <n; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] == m) {
                        // 시작위치와, 등산로 개수
                    	v[r][c] = true;
                        dfs(r, c, 1, 1);
                        v[r][c] = false;
                    }
                }
            }
            // 지도 제대로 나옴.
            // print(map);
            System.out.println("#" + tc + " " + result);
        }
    }
    
    // 일단 모든 경로를 구해보자
    // 벽을 허물지 않는 경우.

    private static void dfs(int r, int c, int cnt, int cut) {
        // 돌아가거나 멈출 조건 // 멈췄을 때 최대 등산로 개수이면 변경
        
        
        // 이동 로직.
        // 상하좌우를 탐색해서 이동한 곳이 k차이면 이동이 가능하고
        // 벽을 한 번만 부술 수 있다. 
    	
        
//        System.out.println("r, c:" + r + "," + c);
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            
            if (0 <= nr && nr < n && 0 <= nc && nc < n && !v[nr][nc]) {
                if (map[r][c] > map[nr][nc]) {
                    v[nr][nc] = true;
                    dfs(nr, nc, cnt+1, cut);
                    v[nr][nc] = false;
                }
                
                else if (map[r][c] <= map[nr][nc] && cut == 1) {
                    for(int j = 1; j <= k; j++) {
                    	if (map[nr][nc] - j < map[r][c]) {
                    		map[nr][nc] -= j;
                            dfs(nr, nc, cnt+1, cut-1);
                            map[nr][nc] += j;
                    	}
                    }
                }
            }
        }
        
        if (cnt > result) {
    		result = cnt;
        }
        
    }

    private static void print(int[][] map) {
        for (int r = 0; r <n; r++) {
            System.out.println(Arrays.toString(map[r]));
        }
    }
    
}
