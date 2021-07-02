package wpi.many.tests.ensures.called.methods;

import org.checkerframework.checker.calledmethods.qual.CalledMethods;

public class App {

    void requiresToString(@CalledMethods({"toString"}) Object obj) { }

    // This method is intentionally unannotated. WPI should infer the following annotation,
    // which will allow the test() method to compile: @EnsuresCalledMethods(expr="#1", methods="toString")
    void ensuresToStringIsCalled(Object obj) {
        obj.toString();
    }

    void test() {
        Object obj = new Object();
        ensuresToStringIsCalled(obj);
        requiresToString(obj);
    }
}
