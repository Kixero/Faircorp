package com.emse.spring.faircorp.model;

public class PairDto {

    private final double x;
    private final double y;

    public PairDto()
    {
        x = 0;
        y = 0;
    }

    public PairDto(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}