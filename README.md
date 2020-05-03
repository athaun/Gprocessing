## Gprocessing is a simple java graphics library built on top of LWJGL, modeled after the processing library, but with an emphasis on performance.

### Performance vs Processing 3
Running the same intensive code at a resolution of 1920x1500, Gprocessing gets an average of 24 FPS, while processing gets an average of 6 FPS
```java
int size = 2;
float pos = 0;
void test() {
    for (int i = 0; i < width / size; i += 2) {
        for (int j = 0; j < height / size; j += 2) {
            fill(random(0, 255));
            rect(i * size, j * size + 1, size, size);
        }
    }
    pos += 5;
    if (pos > height) {
        pos = 0;
    }
    fill(0, 0, 0);
    rect(0, pos, width, 100);
}
```
Gprocessing (Left), Processing 3 (Right)
![screenshot](Performance.png)

### Changelog:

+ 5.1.2020
- setup glfw and lwjgl
- Created window class
- setup game loop
- setup basic graphics
- created rect() method
- created pressure test program<br>
+ 5.2.2020
- created engine class
- added FPS counter
- added static class imports to better resemble processing method calls
- added static misc methods: init, map, random, round, println
- cleaned up initialization
- added FPS counter
- created Vector 2D and 3D classes
- added graphics class
- added static methods: fill(), fillAlpha(), and background()
- cleaned up window class
- created github repo
