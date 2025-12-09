#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_NAME_LEN 100
#define INITIAL_CAPACITY 2

char **read_names(int *num_names);

void free_names(char **names, int num_names) {
    for (int i = 0; i < num_names; i++) {
        free(names[i]);
    }
    free(names);
}

int main() {
    int num_names = 0;
    char **names = read_names(&num_names);

    printf("\nYou entered %d name(s):\n", num_names);
    for (int i = 0; i < num_names; i++) {
        printf("%s\n", names[i]);
    }

    free_names(names, num_names);
    return 0;
}


// ------------------------
// read_names()
// ------------------------

char **read_names(int *num_names) {
    int capacity = INITIAL_CAPACITY;

    // Create an array of char* to hold names
    char **names = malloc(capacity * sizeof(char *));
    if (!names) {
        fprintf(stderr, "Initial allocation failed!\n");
        exit(EXIT_FAILURE);
    }

    char temp[MAX_NAME_LEN];
    printf("Enter student names.\n");

    while (fgets(temp, MAX_NAME_LEN, stdin)) {

        // Expand array if needed
        if (*num_names >= capacity) {
            capacity *= 2;
            char **temp_names = realloc(names, capacity * sizeof(char *));
            if (!temp_names) {
                fprintf(stderr, "Reallocation failed!\n");
                free_names(names, *num_names);
                exit(EXIT_FAILURE);
            }
            names = temp_names;
        }

        // Allocate memory for the new name
        names[*num_names] = malloc(MAX_NAME_LEN * sizeof(char));
        if (!names[*num_names]) {
            fprintf(stderr, "Allocation for new name failed!\n");
            free_names(names, *num_names);
            exit(EXIT_FAILURE);
        }

        // Copy the input string into allocated memory
        strncpy(names[*num_names], temp, MAX_NAME_LEN);

        // Increment count
        (*num_names)++;
    }

    return names;
}
