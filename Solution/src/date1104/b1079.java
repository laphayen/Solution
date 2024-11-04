package date1104;

import java.util.Arrays;
import java.util.Scanner;

public class b1079 {
	
	static int n;
	static int [] g;
	static int [][] R;
	
	static boolean [] v;
	
	static int result;
	
	static int mafia;
	
	public static void main(String[] args) {
		
		
		System.setIn(b1079.class.getResourceAsStream("b1079.txt"));
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		g = new int [n];
		R = new int [n][n];
		v = new boolean [n]; // 죽었는지 확인하는 배열.
		
		result = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; i++) {
			v[i] = true;
		}
		
		for (int i = 0; i < n; i++) {
			g[i] = sc.nextInt();
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c <n; c++) {
				R[r][c] = sc.nextInt();
			}
		}
		
		mafia = sc.nextInt();
		
		
		recur(n, 0);
		
		
		System.out.println(result);
	}


	private static void recur(int people, int day) {
		
		// 게임이 종료 되는 조건 . 
		if (!v[mafia] || people == 1) {
//			System.out.println(day);
			if (day > result) {
				result = day;
			}
			return;
		}
		
//		System.out.println(people);
		// 밤인 경우.
		// 한 명 죽이고 다음 재귀로 넘어가.
		if (people % 2 == 0) {
			
			// 순서대로 한 명씩 죽여본다.
			for (int i = 0; i < people; i++) {
				
				if (v[i] == false || mafia == i) {
					continue;
				}
				
				
				
				for (int j = 0; j < n; j++) {
					if (!v[j]) {
						continue;
					}
					g[j] += R[i][j];
				}
				
				v[i] = false;
				recur(people-1, day+1);
				v[i] = true;
				
				for (int j = 0; j < n; j++) {
					if (!v[j]) {
						continue;
					}
					g[j] -= R[i][j];
				}
				
			}
		}
		
		// 낮인 경우.
		// 점수가 가장 좋은 사람을 죽인다.
		else {
			int maxN = Integer.MIN_VALUE;
			int target = n-1;
			
			for (int i = 0; i < n; i++) {
				if (v[i] && maxN < g[i]) {
					maxN = g[i];
					target = i;
				}
				else if ( v[i] && maxN == g[i]) {
					target = Math.min(i, target);
				}
			}
			
			v[target] = false;
			recur(people-1, day);
			v[target] = true;
		}
		
	}

}	
