package com.d3h.validation.creator;

public class CreatorFactory {
    public static ICreator getDefaultCreator() {
        return CGlibClassCreator.getInstance();
    }
}
