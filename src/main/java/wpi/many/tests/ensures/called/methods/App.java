package wpi.many.tests.ensures.called.methods;

import org.checkerframework.checker.calledmethods.qual.CalledMethods;

public class App {

    Object obj;

    void requiresToString(@CalledMethods({"toString"}) Object obj) { }

    // This method is intentionally unannotated. WPI should infer the following annotation,
    // which will allow the test() method to compile: @EnsuresCalledMethods(expr="this.obj", methods="toString")
    void ensuresToStringIsCalled() {
        this.obj.toString();
    }

    void test() {
        obj = new Object();
        ensuresToStringIsCalled();
        requiresToString(obj);
    }
}
