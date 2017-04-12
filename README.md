### About
**FridaSandbox** is a small Android application that I used to learn how to hook functions with [Frida](https://www.frida.re/ "Frida webpage").

I tried to implement different situations that can be interesting to use Frida hooks (if you have more ideas please feel free to contribute). Each section is implemented in three diferent ways: in the FridaSandbox app, in the FridaLib (a Java library) and in native code using the JNI (*I know that is redundant since a lot of Frida hooks will have the same code, but Frida is an awesome tool and it's funny to use :D*).

### Basics
Frida is a dynamic code instrumentation toolkit, that allows to inject JavaScript code in an immense variety of environments (Android, Linux, Windows, QNX, macOS and iOS). In this brief introduction to Frida, I only will focus on how to use it on Android applications (especially dealing with Java and sometimes with native code).

The resource that I used to learn the basics about how to use Frida in Android environments is the official [JavaScript API documentation](https://www.frida.re/docs/javascript-api/#java).

### Sections
#### Hook
Firda can be used for a most basic functions, for example to trace the application functions to know how the program is working and when the functions are been called.

```javascript
if (Java.available) {
    Java.perform(function () {
    });
}
```

#### Implement
TODO: write section

#### Brute force
TODO: write section

#### Security checks
TODO: write section
