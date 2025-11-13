#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LINE_BUFFER_SIZE 128
#define NUM_LINES_BUFFER_SIZE 5

// Use realloc

char** readlines(int* num_lines);
char* readline();
void printlines(char** lines, int num_lines);
void freelines(char** lines, int num_lines);
int cmp(const void* a, const void* b);

// Use qsort (provide: pointer to arr, num elements, size of elem, function)
// Use strcmp(str1, str2)  (1 < 2: -, 1 > 2: +)

// Main function for sorting program
int main() {
    int num_lines = 0;
    char** lines = readlines(&num_lines);

    // Sort lines based on cmp (so input is valid)
    // (array, # elements, size of element, function for sorting)
    qsort(lines, num_lines, sizeof(char*), cmp);

    printlines(lines, num_lines);
    freelines(lines, num_lines);
    return 0;
}

// Read line from standard input and assign to buffer, reallocate more memory for buffer if necessary
char* readline() {
    // Declare Buffer
    char* buffer = malloc(sizeof(char) * LINE_BUFFER_SIZE);
    int curr_buffer_size = LINE_BUFFER_SIZE;
    // Index for buffer
    int i = 0;

    if (buffer == NULL) {
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }
    
    // Read characters and append to buffer until finished or enter
    int c;
    while (((c = getchar()) != EOF) && c != '\n') {
        if (i >= curr_buffer_size - 1) {
            char *test = realloc(buffer, (curr_buffer_size * 2) * sizeof(char));
            if (test == NULL) {
                fprintf(stderr, "Memory allocation failed!\n");
                free(buffer);
                exit(EXIT_FAILURE);
            } else {
                buffer = test;
                curr_buffer_size = curr_buffer_size * 2;
            }
        }
        
        buffer[i] = c;
        i++;
    }
    // Add null terminator to end so string is valid
    buffer[i] = '\0';

    // If index is 0 and character is EOF, we are done reading input
    if (i == 0 && c == EOF) {
        free(buffer);
        return NULL;
    } else {
        return buffer;
    }
}

// Read all lines from standard input and store in buffer, reallocate more memory to buffer if needed
char** readlines(int* num_lines) {
    // Allocate maximum possible memory of user input
    char** buffer = malloc(sizeof(char*) * NUM_LINES_BUFFER_SIZE);
    int curr_buffer_size = NUM_LINES_BUFFER_SIZE;
    int i = 0;

    // Incriment value at num_lines, only for up to 5 lines
    char* line;
    while ((line = readline()) != NULL) {
        // Check if i == bufferSize - , since if i == buffer size will read incalid memory
        if (i == curr_buffer_size) {
            // Make buffer twice bigger based off local buffer_size variable
            char** test = realloc(buffer, sizeof(char*) * (curr_buffer_size * 2));

            // Test if realloc successful
            if (test == NULL) {
                fprintf(stderr, "Memory allocation failed!\n");
                exit(EXIT_FAILURE);
            } else {
                buffer = test;
            }
            // Modify curr_buffer_size in-case buffer is exceeded again
            curr_buffer_size = curr_buffer_size * 2;
        }
        buffer[i] = line;
        i += 1;
    }
    *num_lines = i;
    return buffer;
}

// Print all lines from standard input in sorted order
void printlines(char** lines, int num_lines) {
    // Dereference the values stores at every pointer in the main heap array
    for (int i = 0; i < num_lines; i++) {
        // Not necessary to dereference
        printf("%s\n", lines[i]);
    }
}

// Free all memory to prevent leaks
void freelines(char** lines, int num_lines) {
    // Free all lines
    for (int i = 0; i < num_lines; i++) {
        free(lines[i]);
    }
    free(lines);
    lines = NULL;
}

// Comparison Function
int cmp(const void* a, const void* b) {
    const char* str1 = *(const char**)a;
    const char* str2 = *(const char**)b;
    return strcmp(str1, str2);
}
