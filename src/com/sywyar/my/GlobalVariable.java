/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sywyar.my;

import com.sywyar.my.api.Command;

import java.util.HashMap;

public class GlobalVariable extends Thread{
    protected static HashMap<Command,String> commandPluginName=new HashMap<>();
    protected static HashMap<String, Command> commands = new HashMap<>();
}
