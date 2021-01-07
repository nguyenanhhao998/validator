package com.validation.creator;

public interface ICreator {
    <T> T create(Class<T> clazz);
    <T> T create(Class<T> clazz, Object[] args);
}
