package me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Getter
public class WrappedMethod {
    private final WrappedClass parent;
    private final Method method;
    private final String name;
    private final List<Class<?>> parameters;

    public WrappedMethod(WrappedClass parent, Method method) {
        this.parent = parent;
        this.method = method;
        this.name = method.getName();
        this.parameters = Arrays.asList(method.getParameterTypes());
    }

    public <T> T invoke(Object object, Object... args) {
        try {
            return (T) this.method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
        //    e.printStackTrace();
        }
        return null;
    }

    public int getModifiers() {
        return this.method.getModifiers();
    }
}
