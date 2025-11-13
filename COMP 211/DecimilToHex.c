#include <stdio.h>
#include <stdlib.h>

#define FULL_SECOND_NIBBLE 15
#define LETTER_OFFSET 10
#define SINGLE_DIGIT_HEX 16

int main() {
    int char_count = 0;
    int c;

    while ((c = getchar()) != EOF) {
        // Track character count
        if (c < SINGLE_DIGIT_HEX) {
            char_count++;
        } else {
            char_count += 2;
        }

        // Delete second nibble
        int h1 = c >> 4;
        // Sets forst nibble to off
        int h2 = c & FULL_SECOND_NIBBLE;

        // Convert ASCII to printable character
        if (h1 <= 9 && h1 >= 0) {
            h1 += '0';
        } else if (h1 <= 15 && h1 >= 10) {
            // Offset since A starts at 10
            h1 += 'A';
            h1 -= LETTER_OFFSET;
        }
        if (h2 <= 9 && h2 >= 0) {
            h2 += '0';
        } else if (h2 <= 15 && h2 >= 10) {
            h2 += 'A';
            h2 -= LETTER_OFFSET;
        }

        putchar(h1);
        putchar(h2);

        if (char_count >= 80) {
            putchar('\n');
            char_count = 0;
        }

        fflush(stdout);
    }
    putchar('\n');
}
