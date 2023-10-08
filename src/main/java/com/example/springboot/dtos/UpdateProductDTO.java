package com.example.springboot.dtos;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record UpdateProductDTO(@Nullable String name, @Nullable BigDecimal value) {
}
