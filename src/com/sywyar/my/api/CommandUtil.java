/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sywyar.my.api;

import com.sywyar.my.GlobalVariable;
import com.sywyar.my.rsa.Base64;
import com.sywyar.my.rsa.RSAUtils;
import com.sywyar.superjsonobject.SuperJsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class CommandUtil extends GlobalVariable {
    private static boolean hasKey =false;
    private static String publicKey = "";
    private static String privateKey="";
    private static String serverPublicKey = "";
    public static void tokenSendCommand(String token) throws Exception {
        Socket socket = new Socket("121.89.215.114",5010);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream()){
            @Override
            public void write(String s) {
                if (hasKey){
                    s = RSAUtils.encrypt(s,serverPublicKey,false);
                }
                write(s, 0, s.length());
            }
        };
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())){
            @Override
            public String readLine() throws IOException {
                String s = super.readLine();
                if (hasKey){
                    s = RSAUtils.decrypt(s,privateKey,false);
                }
                return s;
            }
        };
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(2048, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKeyRSA = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKeyRSA = (RSAPublicKey) keyPair.getPublic();
        publicKey=new String(Base64.getEncoder().encode(publicKeyRSA.getEncoded()));
        privateKey=new String(Base64.getEncoder().encode(privateKeyRSA.getEncoded()));

        SuperJsonObject jsonObject = new SuperJsonObject();
        jsonObject.addProperty("type","getKey-useCA");
        printWriter.write(jsonObject.toString());
        printWriter.println();
        printWriter.flush();
        SuperJsonObject code = new SuperJsonObject(bufferedReader.readLine());
        if (!RSAUtils.caPu(code.getAsString("code0"),ca).equals(code.getAsString("code1"))){
            System.err.println("网络异常，程序终止！");
            throw new IOException("密钥核对错误");
        }
        serverPublicKey = code.getAsString("code1");

        hasKey = true;

        printWriter.write(publicKey);
        printWriter.println();
        printWriter.flush();

        SuperJsonObject req = new SuperJsonObject();
        token = token.replace("\n","");
        req.addProperty("token",token);
        req.addProperty("type","tokenSendCommand");
        printWriter.write(req.toString());
        printWriter.println();
        printWriter.flush();

        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
