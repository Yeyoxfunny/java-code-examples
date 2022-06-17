package com.smorales.utils;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ImmutableCollections {

    private ImmutableCollections() {
        throw new UnsupportedOperationException();
    }

    public static <K, V> Map.Entry<K, V> entry(K k, V v) {
        return new AbstractMap.SimpleImmutableEntry<>(
                Objects.requireNonNull(k),
                Objects.requireNonNull(v)
        );
    }

    @SafeVarargs
    public static <K, V> Map<K, V> mapOf(Map.Entry<? extends K, ? extends V>... entries) {
        return Collections.unmodifiableMap(mapOfEntries(entries));
    }

    @SafeVarargs
    private static <K, V> Map<K, V> mapOfEntries(Map.Entry<? extends K, ? extends V>... entries) {
        return Stream.of(Objects.requireNonNull(entries))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @SafeVarargs
    public static <T> Set<T> setOf(T... elements) {
        return Collections.unmodifiableSet(setOfElements(elements));
    }

    @SafeVarargs
    private static <T> Set<T> setOfElements(T... elements) {
        return new HashSet<>(Arrays.asList(Objects.requireNonNull(elements)));
    }

}
