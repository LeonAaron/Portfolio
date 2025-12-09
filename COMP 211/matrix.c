#include <stdio.h>
#include <stdlib.h>

int main() {
    int rows, cols;

    // Step 1: Get matrix dimensions from the user
    printf("Enter number of rows: ");
    scanf("%d", &rows);
    printf("Enter number of columns: ");
    scanf("%d", &cols);

    // Step 2: Allocate memory for the array of row pointers
    int **matrix = malloc(rows * sizeof(int *));
    if (matrix == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    // Step 3: Allocate memory for each row
    for (int i = 0; i < rows; i++) {
        matrix[i] = malloc(cols * sizeof(int));
        if (matrix[i] == NULL) {
            printf("Memory allocation failed.\n");

            // Free any rows that were already allocated
            for (int j = 0; j < i; j++) {
                free(matrix[j]);
            }
            free(matrix);
            return 1;
        }

        // Step 4: Fill the matrix with i * j
        for (int j = 0; j < cols; j++) {
            matrix[i][j] = i * j;
        }
    }

    // Step 5: Print the matrix
    printf("\nMatrix:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }

    // Step 6: Free memory
    for (int i = 0; i < rows; i++) {
        free(matrix[i]);
    }
    free(matrix);

    return 0;
}


