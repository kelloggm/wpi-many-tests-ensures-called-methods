package wpi.many.tests.ensures.called.methods;

import org.checkerframework.checker.calledmethods.qual.CalledMethods;

public class App {

    Object obj;

    void requiresToString(@CalledMethods({"toString"}) Object obj) { }

    void requiresToStringAndHashCode(@CalledMethods({"toString", "hashCode"}) Object obj) { }

    // These methods are intentionally unannotated. WPI should infer @EnsuresCalledMethods annotation,
    // which will allow the test_*() methods to compile.
    void ensuresToStringIsCalled() {
        this.obj.toString();
    }

    void ensuresToStringIsCalledOnParam(Object obj1) {
        obj1.toString();
    }

    void ensuresToStringIsCalledOnThis() {
        this.toString();
    }

    void ensuresToStringIsCalledOnParams(Object obj1, Object obj2) {
        obj1.toString();
        obj2.toString();
    }

    void ensuresToStringAndHashCodeIsCalledOnParam(Object obj1) {
        obj1.toString();
        obj1.hashCode();
    }

    // test methods

    void test_field_1() {
        obj = new Object();
        ensuresToStringIsCalled();
        requiresToString(obj);
    }

    void test_param_1() {
        Object obj1 = new Object();
        ensuresToStringIsCalledOnParam(obj1);
        requiresToString(obj1);
    }

    void test_param_2() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        ensuresToStringIsCalledOnParam(obj1);
        ensuresToStringIsCalledOnParam(obj2);
        requiresToString(obj1);
        requiresToString(obj2);
    }

    void test_param_3() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        ensuresToStringIsCalledOnParams(obj1, obj2);
        requiresToString(obj1);
        requiresToString(obj2);
    }

    void test_param_4() {
        Object obj1 = new Object();
        ensuresToStringAndHashCodeIsCalledOnParam(obj1);
        requiresToString(obj1);
    }

    void test_param_5() {
        Object obj1 = new Object();
        ensuresToStringAndHashCodeIsCalledOnParam(obj1);
        requiresToStringAndHashCode(obj1);
    }

    void test_this() {
        ensuresToStringIsCalledOnThis();
        requiresToString(this);
    }
}
