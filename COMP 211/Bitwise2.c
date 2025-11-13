#include <stdio.h>
#include <stdlib.h>

#define EVEN_CHECKER 2

int main() {
    int c;
    while ((c = getchar()) != EOF) {
        int one_count = 0;
        int c_copy = c;

        while (c_copy != 0) {
            one_count += c_copy % EVEN_CHECKER;
            c_copy = c_copy >> 1;
        }

        // Corrupted File
        if (one_count % EVEN_CHECKER != 0) {
            printf("\nCorruption detected!\n");
            return EXIT_FAILURE;
        }

        // Set c to regular ASCII character
        c = c >> 1;

        putchar(c);
        fflush(stdout);
    }
    putchar('\n');
}
