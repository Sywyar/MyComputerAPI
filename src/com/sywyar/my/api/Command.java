/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sywyar.my.api;

import com.sywyar.superjsonobject.SuperJsonObject;

public interface Command {
    String getCommandType();
    CommandDescription getCommandDescription();
    String getMyComputerPluginID();
    void run(SuperJsonObject info);
}
