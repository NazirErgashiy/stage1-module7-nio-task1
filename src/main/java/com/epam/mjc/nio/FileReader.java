package com.epam.mjc.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String collector = "";

        try (RandomAccessFile aFile = new RandomAccessFile(file, "r")) {
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);

            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                for (int i = 0; i < byteBuffer.limit(); i++) {
                    collector += (char) byteBuffer.get();
                }
            }
            byteBuffer.clear();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String separate[] = collector.split(System.getProperty("line.separator"));
        String name = separate[0].replace("Name: ", "");
        String age = separate[1].replace("Age: ", "");
        String email = separate[2].replace("Email: ", "");
        String phone = separate[3].replace("Phone: ", "");
        return new Profile(name, Integer.parseInt(age), email, Long.parseLong(phone));
    }
}
