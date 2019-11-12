package com.lgren.jama;

import Jama.Matrix;
import org.junit.Test;

/**
 * TODO
 * @author lgren
 * @since 2019-10-23 16:17
 */
public class JamaTest1 {
    @Test
    public void test1() {
        int pageNum = 6;

        double[] AT = {1D/2, 1D/3, 1D, 1D/4, 1D/5, 1D/5};
        Matrix matrix_A_T = new Matrix(AT, pageNum).transpose();
        matrix_A_T.print(pageNum, 2);

        double[] Sv_1 = {1D, 1D, 1D, 1D, 1D, 1D};
        Matrix matrix_Sv_1 = new Matrix(Sv_1, pageNum);
        matrix_Sv_1.print(pageNum, 2);

        System.out.println("----------开始计算----------");
        Matrix matrix_Sv = handleSv(pageNum, matrix_A_T, matrix_Sv_1, 12);
        System.out.println("----------结束计算----------");
    }
    @Test
    public void test2() {
        int pageNum = 3;

        double[] AT = {1D/2, 1D/3, 1D};
        Matrix matrix_A_T = new Matrix(AT, pageNum).transpose();
        matrix_A_T.print(pageNum, 2);

        double[] Sv_1 = {1D, 1D, 1D};
        Matrix matrix_Sv_1 = new Matrix(Sv_1, pageNum);
        matrix_Sv_1.print(pageNum, 2);

        System.out.println("----------开始计算----------");
        Matrix matrix_Sv = handleSv(pageNum, matrix_A_T, matrix_Sv_1, 12);
        System.out.println("----------结束计算----------");
    }

    private Matrix handleSv(int pageNum, Matrix matrix_A_T, Matrix matrix_Sv_1, int forNum) {
        Matrix matrix_Sv_temp = (Matrix) matrix_Sv_1.clone();
        matrix_Sv_temp.transpose().print(pageNum, 2);
        // for (int i = 0; i < forNum; i++) {
        //     double Sv_1_allValue = matrix_A_T.times(matrix_Sv_temp).get(0, 0);
        //     for (int j = 0; j < pageNum; j++) {
        //         matrix_Sv_temp_temp.set(j, 0, Sv_1_allValue - matrix_A_T.get(0, j) * matrix_Sv_temp.get(j, 0));
        //     }
        //     matrix_Sv_temp = matrix_Sv_temp_temp;
        //     matrix_Sv_temp.transpose().print(pageNum, 2);
        // }
        return matrix_Sv_temp;
    }
}
