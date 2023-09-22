/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sywyar.my.api;

import com.sywyar.superjsonobject.SuperJsonObject;

public class CommandDescription {
    private final SuperJsonObject description = new SuperJsonObject();

    public void setSimplifiedChineseDescription(String description){
        this.description.addProperty("zh_cn",description);
    }

    public void setTraditionalChineseDescription(String description){
        this.description.addProperty("zh_tw",description);
    }

    public void setEnglishDescription(String description){
        this.description.addProperty("en_us",description);
    }

    public void setJapaneseDescription(String description){
        this.description.addProperty("ja",description);
    }

    @Override
    public String toString() {
        return description.toString();
    }
}
