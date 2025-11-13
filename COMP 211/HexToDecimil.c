#include <stdio.h>
#include <stdlib.h>

// Prototypes
int hex_to_decimil(int c);

#define LETTER_OFFSET 10
#define MULT_16_SHIFT_LEFT 4


int main(){ 
    //Program
    int c1;
    int c2;
    while ((c1 = getchar()) != EOF) {
        //Valid since hex always even
        c2 = getchar();

        //Ignore newline characters
        if (c1 == '\n') {
            c1 = c2;
            c2 = getchar();
        }
        if (c2 == '\n') {
            c2 = getchar();
        }

        c1 = hex_to_decimil(c1);
        c2 = hex_to_decimil(c2);

        //Final result- least significant character same and most significant multiplied by 16
        putchar(c2 + (c1 << MULT_16_SHIFT_LEFT));
        fflush(stdout);
    }
    printf("\n");
    fflush(stdout);
    return EXIT_SUCCESS;
}



//Convert hex_to_decimil
int hex_to_decimil(int c) {
    //Get decimil values
    if ('0' <= c && c <= '9'){
        c -= '0';
    } else if ('a' <= c && c <= 'f') {
        c -= 'a';
        c += LETTER_OFFSET;
    } else if ('A' <= c && c <= 'F') {
        c -= 'A';
        //letter by 10
        c += LETTER_OFFSET;
    }
    return c;
}
