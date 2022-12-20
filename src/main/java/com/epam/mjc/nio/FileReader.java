package com.epam.mjc.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;


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

        String regex[] = collector.split(System.getProperty("line.separator"));
        String name = regex[0].replace("Name: ", "");
        String age = regex[1].replace("Age: ", "");
        String email = regex[2].replace("Email: ", "");
        String phone = regex[3].replace("Phone: ", "");

        return new Profile(name, Integer.parseInt(age), email, Long.parseLong(phone));
    }
}
