#include <stdio.h>
#include <stdlib.h>

#define EVEN_CHECKER 2

int main(){

    int c;
    while ((c = getchar()) != EOF) {
        int one_count = 0;
        int c_copy = c;
        while (c_copy != 0){
            //If c_copy % 2 = 1, the least significant bit is 1
            one_count += c_copy % EVEN_CHECKER;
            c_copy = c_copy >> 1;
        }
        //if even
        if (one_count % EVEN_CHECKER == 0){
            c = c << 1;
        } else {
            //if odd
            c = (c << 1) | 1;
        }

        putchar(c);
        fflush(stdout);
    }
    putchar('\n');
}
