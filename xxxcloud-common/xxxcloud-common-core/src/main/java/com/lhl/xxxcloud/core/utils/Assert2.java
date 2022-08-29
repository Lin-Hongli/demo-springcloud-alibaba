package com.lhl.xxxcloud.core.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author Linhongli
 */
public abstract class Assert2 {

    public static void state(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }

    public static void isTrue(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }

    public static void isNull(@Nullable Object object, RuntimeException e) {
        if (object != null) {
            throw e;
        }
    }

    public static void notNull(@Nullable Object object, RuntimeException e) {
        if (object == null) {
            throw e;
        }
    }

    public static void hasLength(@Nullable String text, RuntimeException e) {
        if (!StringUtils.hasLength(text)) {
            throw e;
        }
    }

    public static void hasText(@Nullable String text, RuntimeException e) {
        if (!StringUtils.hasText(text)) {
            throw e;
        }
    }

    public static void doesNotContain(@Nullable String textToSearch, String substring, RuntimeException e) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw e;
        }
    }

    public static void notEmpty(@Nullable Object[] array, RuntimeException e) {
        if (ObjectUtils.isEmpty(array)) {
            throw e;
        }
    }

    public static void noNullElements(@Nullable Object[] array, RuntimeException e) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw e;
                }
            }
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, RuntimeException e) {
        if (CollectionUtils.isEmpty(collection)) {
            throw e;
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, RuntimeException e) {
        if (CollectionUtils.isEmpty(map)) {
            throw e;
        }
    }

}
