package com.scorer.clientPhone.watch;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConSocket {

    private static final int MONITORPORT = 20001;


    public static void main(String[] args) {
        new Thread(() -> new ConSocket().server()).start();
    }

    private void server() {
        try {
            //创建服务器socket
            ServerSocket serverSocket = new ServerSocket(MONITORPORT);
            while (true) {
                System.out.println("已启动,监听端口号:" + MONITORPORT);
                Socket socketReceive = serverSocket.accept();
                new Thread(new operateMSG(socketReceive)).start();
            }
        } catch (IOException ignored) {
        }
    }

    private void SaveOutputStream(String Key, String DeviceInfo, OutputStream watchOutputStream) {
        WatchOutputInfo watchOutputInfo = new WatchOutputInfo(Key, DeviceInfo, watchOutputStream);
    }

    class operateMSG extends Thread {

        private Socket socket;

        operateMSG(Socket socket) {
            super();
            this.socket = socket;
        }

        void BackMSG(OutputStream outputStream, String msg) throws IOException {
            String outStr = ("返回信息：[" + msg + "]");
            System.out.println(outStr);
            outputStream.write(outStr.getBytes("GB2312"));
        }

        @Override
        public void run() {
            InputStream input = null;
            OutputStream outputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                this.socket.setKeepAlive(true);
                System.out.println("收到信息");
                input = this.socket.getInputStream();
                SocketAddress remoteSocketAddress = this.socket.getRemoteSocketAddress();
                System.out.println("信息地址: " + remoteSocketAddress);
                int i = 1;
                byte[] bytes = new byte[1];
                while (input.read(bytes) != -1) {
                    if (bytes[0] == 91) {
                        String Key = readInputStreamWatch(input);
                        System.out.println("Key: " + Key);
                        String DeviceInfo = readInputStreamWatch(input);
                        System.out.println("DeviceInfo: " + DeviceInfo);
                        String LEN_Read16 = readInputStreamWatch(input);
                        long Len = Long.parseLong(LEN_Read16, 16);
                        System.out.println("LenStr: " + LEN_Read16);
                        System.out.println("Len: " + Len);
                        String Command = readInputStreamWatchCommand(input);
                        System.out.println("Command: " + Command);
                        outputStream = socket.getOutputStream();
                        SaveOutputStream(Key, DeviceInfo, outputStream);
                        String content = null;
                        switch (Command) {
                            case "LK":
                                BackMSG(outputStream, Key + "*" + DeviceInfo + "*" + LEN_Read16 + "*" + "LK");
                                break;
                            case "LGZONE":
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,yyyy-MM-dd");
                                String msgBack = "LGZONE" + ",+8" + "," + sdf.format(new Date());
                                String lenBack = Long.toHexString(msgBack.length()).toUpperCase();
                                lenBack = lenBack.length() > 4 ? lenBack : ("0000" + lenBack).substring(("0000" + lenBack).length() - 4);
                                BackMSG(outputStream, Key + "*" + DeviceInfo + "*" + lenBack + "*" + msgBack);
                                break;
                            case "TK": {
                                File temp_path = new File("");
                                String file_path = temp_path + File.separator + "amrName_" + (i++) + ".amr";
                                File temp = new File(file_path);
                                fileOutputStream = new FileOutputStream(temp);
                                readInputStreamWatchAMR(input, fileOutputStream, Len - Command.length() - 1);
                                fileOutputStream.close();
                            }
                            break;
                            case "img": {
                                File temp_path = new File("");
                                String file_path = temp_path + File.separator + "imgName_" + (i++) + ".img";
                                File temp = new File(file_path);
                                fileOutputStream = new FileOutputStream(temp);
                                readInputStreamWatchAMR(input, fileOutputStream, Len - Command.length() - 1);
                                fileOutputStream.close();
                            }
                            break;
                            case "UD":      //数据上报
                                content = readInputStreamReadContent(input, (int) (Len));
                                System.out.println(content);
                                break;
                            case "AL":      //报警
                                content = readInputStreamReadContent(input, (int) (Len));
                                System.out.println(content);
                                break;
                            default:
                                content = readInputStreamReadContent(input, (int) (Len));
                                System.out.println(content);
                                break;
                        }
                    } else if (bytes[0] == 93) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (this.socket != null) {
                        this.socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readInputStreamWatch(InputStream input) throws IOException {
        StringBuilder reStr = new StringBuilder();
        byte[] bytes = new byte[1];
        while (input.read(bytes) != -1 && bytes[0] != 42) {
            reStr.append(new String(bytes));
        }
        return reStr.toString();
    }

    private String readInputStreamWatchCommand(InputStream input) throws IOException {
        StringBuilder reStr = new StringBuilder();
        byte[] bytes = new byte[1];
        while (input.read(bytes) != -1 && bytes[0] != 44 && bytes[0] != 93) {//42[*],44[,],93[]]
            reStr.append(new String(bytes));
        }
        return reStr.toString();
    }

    private String readInputStreamReadContent(InputStream input, int strLen) throws IOException {

//        StringBuilder reStr = new StringBuilder();
//        byte[] bytes = new byte[1];
//        while (input.read(bytes) != -1 && bytes[0] != 93) {//42[*],44[,],93[]]
//            reStr.append(new String(bytes));
//        }
//        return reStr.toString();

        String reStr = null;
        if (strLen > 0) {
            byte[] bytes = new byte[strLen];
            if (input.read(bytes, 0, strLen) != -1) {
                reStr = new String(bytes);
            }
        }
        return reStr;
    }

    private void readInputStreamWatchAMR2(InputStream input, FileOutputStream fileOutputStream, long len) throws IOException {
        byte[] bytes = new byte[2];
        long alreadyReadLen = 0;
        int readLen = 0;
        System.out.println("writing...");
        while (alreadyReadLen < len && (readLen = input.read(bytes)) != -1) {
            alreadyReadLen += readLen;
            byte[] outBytes = new byte[readLen];
            outBytes[0] = bytes[0];
            if (readLen > 1) {
                if (bytes[0] == 0X7D)
                    switch (bytes[1]) {
                        case 0X01:
                            System.out.print("0X01 ");
                            outBytes = new byte[1];
                            outBytes[0] = 0X7D;
                            break;
                        case 0X02:
                            System.out.print("0X02 ");
                            outBytes = new byte[1];
                            outBytes[0] = 0X5B;
                            break;
                        case 0X03:
                            System.out.print("0X03 ");
                            outBytes = new byte[1];
                            outBytes[0] = 0X5D;
                            break;
                        case 0X04:
                            System.out.print("0X04 ");
                            outBytes = new byte[1];
                            outBytes[0] = 0X2C;
                            break;
                        case 0X05:
                            System.out.print("0X05 ");
                            outBytes = new byte[2];
                            outBytes[0] = 0X2A;
                            break;
                        default:
                            outBytes[1] = bytes[1];
                            break;
                    }
            }
            fileOutputStream.write(outBytes);
        }
        System.out.println("write over");
    }

    private void readInputStreamWatchAMR(InputStream input, FileOutputStream fileOutputStream, long len) throws IOException {
        byte[] bytes = new byte[1];
        long alreadyReadLen = 0;
        System.out.println("writing...");
        while (alreadyReadLen < len && (input.read(bytes) != -1)) {
            alreadyReadLen++;
            byte[] outBytes;
            switch (bytes[0]) {
                case 0X7D:
                    System.out.print("0X7D ");
                    outBytes = new byte[2];
                    outBytes[0] = 0X7D;
                    outBytes[1] = 0X01;
                    break;
                case 0X5B:
                    System.out.print("0X5B ");
                    outBytes = new byte[2];
                    outBytes[0] = 0X7D;
                    outBytes[1] = 0X02;
                    break;
                case 0X5D:
                    System.out.print("0X5D ");
                    outBytes = new byte[2];
                    outBytes[0] = 0X7D;
                    outBytes[1] = 0X03;
                    break;
                case 0X2C:
                    System.out.print("0X2C ");
                    outBytes = new byte[2];
                    outBytes[0] = 0X7D;
                    outBytes[1] = 0X04;
                    break;
                case 0X2A:
                    System.out.print("0X2A ");
                    outBytes = new byte[2];
                    outBytes[0] = 0X7D;
                    outBytes[1] = 0X05;
                    break;
                default:
                    outBytes = bytes;
                    break;
            }
            fileOutputStream.write(outBytes);
        }
        System.out.println("write over");
    }
}
