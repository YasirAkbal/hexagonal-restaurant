package com.yasirakbal.order.application.domain.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    BigDecimal amount;

    public Money(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null.");

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        this.amount = amount;
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public Money multiply(Money other) {
        return new Money(this.amount.multiply(other.amount));
    }

    public Money multiply(Integer number) {
        BigDecimal numBD = BigDecimal.valueOf(number);
        return new Money(this.amount.multiply(numBD));
    }
}