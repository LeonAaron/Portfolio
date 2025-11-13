#include <stdio.h>

typedef struct {
    int width;
    int length;
    int total_moves;
} Grid;

typedef struct {
    int x;
    int y;
    char Name;
} Robot;

void move_robot(Robot* r, Grid* g, int dx, int dy) {
    int new_x = r->x + dx;
    int new_y = r->y + dy;

    if (new_x < g->width && new_x >= 0 && new_y < g->length && new_y >= 0) {
        r->x = new_x;
        r->y = new_y;
        g->total_moves++;
        printf("Robot %c moved to (%d, %d). Grid total %d\n", r->Name, r->x, r->y, g->total_moves);
    } else {
        printf("Out of bounds\n");
    }

}

int main() {
    Robot r = {0, 0 ,'A'};
    Grid g = {5, 5, 0};

    move_robot(&r, &g, 1, 0);
    move_robot(&r, &g, 0, 1);
    move_robot(&r, &g, 10, 1);

    printf("Final position of Robot %c: (%d, %d)\n", r.Name, r.x, r.y);
    printf("Grid recorded %d moves\n", g.total_moves);

}
