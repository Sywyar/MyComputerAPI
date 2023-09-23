/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sywyar.my.api;

import com.sywyar.my.GlobalVariable;

public class RegisterCommand extends GlobalVariable {
    private RegisterCommand(){}

    public static void addCommand(Command command) throws CommandTypeException {
        if (!commands.containsKey(command.getMyComputerPluginID()+":"+command.getCommandType())){
            if (command.getMyComputerPluginID()!=null && !command.getMyComputerPluginID().isEmpty()){
                if (!command.getCommandType().contains(":") && !command.getCommandType().contains("(") && !command.getCommandType().contains(")") && !command.getMyComputerPluginID().contains(":") && !command.getMyComputerPluginID().contains("(") && !command.getMyComputerPluginID().contains(")")){
                    commandPluginName.put(command,command.getMyComputerPluginID());
                    commands.put(command.getMyComputerPluginID()+":"+command.getCommandType(),command);
                }else {
                    throw new CommandTypeException(command.getClass().getCanonicalName()+"命令添加失败!原因:\"getCommandType()与getMyComputerPluginID()\"的返回值中不允许包含\": ( )\"这三种符号!");
                }
            }else {
                throw new CommandTypeException(command.getClass().getCanonicalName()+"命令添加失败!原因:此命令没有MyComputerPluginID");
            }
        }else {
            throw new CommandTypeException(command.getClass().getCanonicalName()+"命令添加失败!原因:与现有的类型重复.现有类型来源:"+commandPluginName.get(commands.get(command.getMyComputerPluginID()+":"+command.getCommandType())));
        }
    }
}
