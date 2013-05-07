package org.thoughtworks.orc;

public abstract class Controller {
    protected abstract <T> T form(Class<T> tClass);

    protected abstract <T> T form(String name);

    protected abstract <T> T session(String name, T value);

    protected abstract <T> T session(String name);

    protected abstract <T> T flash(String name, T value);

    protected abstract <T> T flash(String name);

    protected abstract <T> T model(String name, T value);
}
