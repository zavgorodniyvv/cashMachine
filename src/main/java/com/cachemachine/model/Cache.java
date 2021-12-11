package com.cachemachine.model;

import java.util.Objects;

public class Cache {
    int par;
    int quantity;

    public Cache() {
    }

    public Cache(int par, int quantity) {
        this.par = par;
        this.quantity = quantity;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cache that = (Cache) o;
        return par == that.par && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(par, quantity);
    }

    @Override
    public String toString() {
        return "AddCacheDTO{" +
                "par=" + par +
                ", quantity=" + quantity +
                '}';
    }
}
