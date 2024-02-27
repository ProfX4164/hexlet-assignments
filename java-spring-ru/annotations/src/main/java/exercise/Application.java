package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {

    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        for (Method method : Address.class.getMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String typeClassName = method.getReturnType().getName();
                typeClassName = typeClassName.contains(".") ? typeClassName.substring(typeClassName.lastIndexOf(".") + 1) : typeClassName;
                System.out.println("Method " + method.getName() + " returns a value of type " + typeClassName);
            }
        }
    }
}
