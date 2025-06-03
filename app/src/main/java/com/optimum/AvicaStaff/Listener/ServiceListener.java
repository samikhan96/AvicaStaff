package com.optimum.AvicaStaff.Listener;

public interface ServiceListener<T,E> {

    public void success(T success);
    public void error(E error);
}