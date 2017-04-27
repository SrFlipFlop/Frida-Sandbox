### About
**FridaSandbox** is a small Android application that I used to learn how to hook functions with [Frida](https://www.frida.re/ "Frida webpage").

I tried to implement different situations that can be interesting to use Frida hooks (if you have more ideas please feel free to contribute). Each section is implemented in three diferent ways: in the FridaSandbox app, in the FridaLib (a Java library) and in native code using the JNI (*I know that is redundant since a lot of Frida hooks will have the same code, but Frida is an awesome tool and it's funny to use :D*).

### Basics
Frida is a dynamic code instrumentation toolkit, that allows to inject JavaScript code in an immense variety of environments (Android, Linux, Windows, QNX, macOS and iOS). In this brief introduction to Frida, I only will focus on how to use it on Android applications (especially dealing with Java and sometimes with native code).

The resource that I used to learn the basics about how to use Frida in Android environments is the official [JavaScript API documentation](https://www.frida.re/docs/javascript-api/#java). Also it can be useful to check the [frida-java](https://github.com/frida/frida-java) to understand how works the Java object in the JavaScript API.

The following JavaScript code is an example of the JavaScript API that I use more in the Frida hooks (*as you will see I only use a small part of the immense functionalities of Frida*).
```javascript
if (Java.available) { //1
    Java.perform(function () { //2
        var x = Java.use('app.package.name'); //3
        x.appMethod.implementation = function(param) { //4
            return this.appMethod(param); //5
        };
        
        var i = x.$new(); //6
        i.appMethod('param'); //7
    });
}
```
1. Java.available is used to check if it's possible to use Java when the hook is injected.
2. Java.perform [*"ensure that the current thread is attached to the VM "*](https://www.frida.re/docs/javascript-api/#java) and call the function that will interact with tha application. It's important that all hook code to be inside the callback function.
3. Java.use generate a JavaScript wrapper to the specified class. This method is really important to select the Java objects that will be instrumented.
4. In this line is used the wrapper generated (*app.package.name*) to select an existing function (*appMethod*) and modify the implementation when the function is called. The new implementation is defined in the JavaScript function, in this case it's only returned the result of the original function.
5. The return statement is used to return the result of the new implementation, in this case is used the result of the original function called using *this.appMethod*.
6. Another interesting Frida feature is to generate new instances of a wrapped class. This can be accomplished by using *$new* with the class wrapped variable.
7. It is possible to use the generated instance like a Java object, for example to call a public function.

To attach the Frida with an Android application it is possible to use the Frida command line (*frida -U -l script.js com.app.name*) or using the bindings supported by other programming languages. In my case I have a small Python script to launch multiple JavaScript hooks, but the following code shows the basics to launch a script.

```python
import frida
js = "do something"
p = frida.get_usb_device().attach('com.app.name')
s = p.create_script(js)
s.load()
```

### Sections
#### Hook
Firda can be used for a most basic functions, for example to trace the application functions to know how the program is working and when the functions are been called. For this task it is only needed to use the JavaScript API function **Java.use** (*third comment in the previous example*). Once the function is wrapped it's time to reimplement to send the feedback to the user, this can be done in a different ways:
* Using the JavaScript print function **console.log**.
* Using the **send** function, that sends information to a binded programing language. With Python, the content of the **send** function is handled by the method specified using *script.on('message', on_message)*.
```javascript
if (Java.available) {
    Java.perform(function () {
    });
}
```

#### Implement
During a pentest or a CTF, is very common to find dynamic protections that make more difficult the reverser work. With Frida it's possible to bypass the protections by changing the methods implementations. To do this is the same code as the hook example, the difference is that in this example we will return the result that bypass the protection (*as I mentioned before, many examples of Frida will use a similar code*).

#### Brute force
TODO: write section

#### Security checks
TODO: write section
