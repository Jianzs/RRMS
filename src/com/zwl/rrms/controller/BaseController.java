package com.zwl.rrms.controller;

public abstract class BaseController {
    protected static void exceptionHand(Exception e) {
        e.printStackTrace();
    }
}
