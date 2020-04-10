package com.spdb.firebase.system.exception;

import java.util.UUID;

public class UniqueIdGenerator {

    public static String get() {
        return UUID.randomUUID().toString();
    }
}
