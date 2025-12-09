#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_WORDS 5
#define MAX_WORD_SIZE 9 // 8 + 1 for null terminator

char* insertWord(char* word, char* replacement) {
    char* oldWord = malloc(sizeof(char) * MAX_WORD_SIZE);
    strcpy(oldWord, word);
    strcpy(word, replacement);
    return oldWord;
}

void replaceWithA(char** words) {
    for (int i = 0; i < MAX_WORDS; i++) {
        *(words[i]) = 'a';
    }
}

int main() {
    // allocate space for words
    char** words = malloc(sizeof(char*) * MAX_WORDS);

    // initialize each word
    for (int i = 0; i < MAX_WORDS; i++) {
        char* word = malloc(sizeof(char) * MAX_WORD_SIZE);
        strcpy(word, "hello");
        words[i] = word;
    }

    // call new functions
    char* oldWord = insertWord(words[3], "goodbye");
    replaceWithA(words);

    // free space
    for (int i = 0; i < MAX_WORDS; i++) {
        free(words[i]);
    }
    free(words);
    free(oldWord);

    return EXIT_SUCCESS;
}
