package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.*;


public class GlobalKeyListenerExample implements NativeKeyListener {
    static StringBuilder inputString = new StringBuilder("");
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        System.out.println("Key code" + e.getKeyCode());
        inputString.append(NativeKeyEvent.getKeyText(e.getKeyCode()));


        if (inputString.length() > 50) {
            inputString.append("\n");
            try (FileWriter filewriter = new FileWriter("output.txt", true)) {
                filewriter.append(inputString.toString());
            } catch (Exception ee) {
                System.err.println(ee);
            }
            inputString.setLength(0); // Clear after writing
        }


        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    /*
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

     */



    public static void test111() {

    }


    public static void main(String[] args) {


        //reads previous file


        //everytime a key is pressed it saves.
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    }

    public static String readAfterOpen(){
        File file = new File("output.txt");
        StringBuilder inputString = new StringBuilder("");

        try{
            FileReader filereader = new FileReader(file);
            int data =  0; //contains Byte value

            //Loop to read file
            while(data != -1){ //-1 means no more data to read.
                data = filereader.read();
                inputString = inputString.append(((char) data));
            }
            filereader.close();
        } catch(FileNotFoundException e){
            System.out.println(e.toString() + "Exception caught: Check to see if the input file exist.");
        } catch (IOException e){
            System.out.println(e.toString() + "Exception caught: IO exception");
        }

        return inputString.toString();
    }


}//Enc Class