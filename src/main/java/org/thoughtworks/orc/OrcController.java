package org.thoughtworks.orc;

import java.util.Optional;

public interface OrcController {
    <T> T form(Class<T> tClass);

    String form(String name);

    <T extends Object> void session(String name, T value);

    <T extends Object> Optional<T> session(String name);

    <T extends Object> void model(String name, T value);
}
