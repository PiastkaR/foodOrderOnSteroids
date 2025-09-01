package com.food.order;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    @Test
    void shouldSortArray() {
        //given
        int[] numbers = {6, 4, 23, 3, 67, 56, 0, 8, 1, 2};
        Arrays.sort(numbers);
        //then
        assertEquals(0, numbers[0]);
    }
}
