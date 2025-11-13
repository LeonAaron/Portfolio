#include <stdio.h>
#include <stdlib.h>

#define ROWS 3
#define COLS 3

int main(){
    int matrix[ROWS][COLS];

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            matrix[i][j] = i;
        }
    }

    int *two_d_array1 = mallocate_1();
    int **two_d_array2 = mallocate2();

}

int* mallocate_1() {
    int *two_d_array;

    two_d_array = malloc(sizeof(int) * ROWS * COLS); 
    return two_d_array;
}

int** mallocate2() {
    int **two_d_array;

    two_d_array = malloc(sizeof(int*) * ROWS);

    for (int i = 0; i < ROWS; i++) {
        two_d_array[i] = malloc(sizeof(int) * COLS);
    }
    return two_d_array;
}
