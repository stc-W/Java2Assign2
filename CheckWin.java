public class CheckWin {
    public static int Check(int[][] current) {
        if (CheckOne(current, 1) == 1) {
            return 1;
        }
        if (CheckOne(current, -1) == 1) {
            return 2;
        }
        int t = 0;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (current[k][i] != 0) {
                    t++;
                }
            }
        }
        if (t == 9) {
            return 0;
        }
        return -1;
    }
    public static int CheckOne (int[][] current, int player) {
        for (int k = 0; k < 3; k++) {
            int total1 = 0;
            int total2 = 0;
            for (int i = 0; i < 3; i++) {
                if (current[k][i] == player) {
                    total1++;
                }
                if (current[i][k] == player) {
                    total2++;
                }
                if (total1 == 3 || total2 == 3) {
                    return 1;
                }
            }
        }
        if (current[0][0] == player && current[1][1] == player && current[2][2] == player) {
            return 1;
        }
        if (current[2][0] == player && current[1][1] == player && current[0][2] == player) {
            return 1;
        }
        return -1;
    }
}
