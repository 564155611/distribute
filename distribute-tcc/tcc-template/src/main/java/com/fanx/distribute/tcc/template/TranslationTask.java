package com.fanx.distribute.tcc.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationTask {

    private String name;

    private Object parameter;
}
