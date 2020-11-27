package org.example.foreignKeySubstitution.mapper.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    @Data
    @AllArgsConstructor
    public static class we{
        String ne;
        String de;
    }

    public static void main(String[] args) {
        List<we> wes = Arrays.asList(new we("11", "22"), new we("22", "33"), new we("11", "33"));
        List<String> stringList = wes.stream()
                .filter(o -> "11".equals(o.getNe()))
                .map(o -> {
                    o.setNe("22");
                    return o.getDe();
                })
                .collect(Collectors.toList());
        System.out.println(stringList);
        System.out.println(wes);
    }
}
