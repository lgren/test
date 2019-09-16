package com.lgren.jyyh_jsp.base;


import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * @create 2019-06-21 17:54
 * @since lgren
 */
public interface WithFields<This extends WithFields> {
    // private boolean allFields;
    // private List<String> hiddenItems;
    // private List<String> skipItems;

    boolean isAllFields();

    Span setAllFields(boolean allFields);

    List<String> getHiddenItems();

    This setHiddenItems(List<String> hiddenItems);

    @SuppressWarnings("unchecked")
    default This addHiddenItems(String... hiddenItems) {
        if (ArrayUtils.isNotEmpty(hiddenItems)) {
            List<String> hiddenItems1 = getHiddenItems();
            if (hiddenItems1 == null) {
                hiddenItems1 = new ArrayList<>();
                setHiddenItems(hiddenItems1);
            }
            for (String item : hiddenItems) {
                Optional.ofNullable(item).ifPresent(hiddenItems1::add);
            }
        }
        return (This) this;
    }

    List<String> getSkipItems();

    This setSkipItems(List<String> skipItems);

    @SuppressWarnings("unchecked")
    default This addSkipItem(String... skipItems) {
        if (ArrayUtils.isNotEmpty(skipItems)) {
            List<String> skipItems1 = getSkipItems();
            if (skipItems1 == null) {
                skipItems1 = new ArrayList<>();
                setSkipItems(skipItems1);
            }
            for (String item : skipItems) {
                Optional.ofNullable(item).ifPresent(skipItems1::add);
            }
        }
        return (This) this;
    }
}
