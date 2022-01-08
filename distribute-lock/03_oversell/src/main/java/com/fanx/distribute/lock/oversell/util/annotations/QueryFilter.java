package com.fanx.distribute.lock.oversell.util.annotations;

import com.fanx.distribute.lock.oversell.util.Precise;
import com.fanx.distribute.lock.oversell.util.FieldQueryStrategy;

public @interface QueryFilter {
    Class<? extends FieldQueryStrategy>[] value() default {Precise.class};
}
