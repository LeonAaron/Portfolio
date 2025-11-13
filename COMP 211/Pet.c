#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[32];
    char species[32];
    int age;
    double weight;
} Pet;

typedef struct {
    Pet* data;
    size_t size;
    size_t capacity;
} PetVec;

void init_vector(PetVec* v) {
    v->data = NULL;
    v->size = v->capacity = 0;
}

Pet init_pet(char* name, char* species, int age, double weight) {
    Pet p;
    strcpy(p.name, name);
    strcpy(p.species, species);
    p.age = age;
    p.weight = weight;
    return p;
}

void petvec_push(PetVec* v, Pet p) {
    
    if (v->size == v->capacity) {

        size_t new_size = v->capacity == 0 ? 4 : v->capacity * 2;

        void* temp = realloc(v->data, new_size * sizeof(Pet));

        if (temp == NULL) {
            perror("Realloc failed\n");
            exit(EXIT_FAILURE);
        }

        v->data = temp;
        v->capacity = new_size;
    }
    v->data[v->size++] = p;
}

Pet* petvec_get(PetVec* v, size_t index) {
    if (index >= v->size) {
        fprintf(stderr, "Index %zu in out of range (Size = %zu)\n", index, v->size);
        return NULL;
    }
    return &v->data[index];
}

int main() {
    PetVec v;
    init_vector(&v);
    // Always include the address of a struct
    petvec_push(&v, init_pet("Tim", "Dog", 3, 10.0));
}
