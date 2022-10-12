package com.chris.webfluxdemo.dto;

import java.util.Arrays;
import java.util.Optional;

public enum CalculatorOperations {

    ADD("+") {
        @Override
        public int calculate(int first, int second) {
            return first + second;
        }
    },
    SUBTRACT("-") {
        @Override
        public int calculate(int first, int second) {
            return first - second;
        }
    },
    MULTIPLY("*") {
        @Override
        public int calculate(int first, int second) {
            return first * second;
        }
    },
    DIVIDE("/") {
        @Override
        public int calculate(int first, int second) {
            Integer secondValue = Optional.of(second)
                    .filter(s -> s != 0)
                    .orElseThrow(() -> new ArithmeticException("No divide by 0!!!"));
            return first / secondValue;
        }
    };

    private String code;

    CalculatorOperations(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CalculatorOperations fromCode(final String code) {
        return Arrays.stream(values())
                .filter(o -> o.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("Not found operation for code : %s", code)));
    }

    public abstract int calculate(int first, int second);
}
