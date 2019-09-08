package com.scorer.clientPhone.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

public class SelfByteDecoder extends ByteToMessageDecoder {

    private final int BASE_LENGTH = 34 + 1 + 15 + 1 + 4;        //协议头 [4a3b07bba36909f39cabee7fc81ea844ZF*353505180089923*000D
    private final byte startData = 0X5B;                              //协议start '['
    private final byte endData = 0X5D;                                //协议end   ']'
    private final byte separator = 0X2A;                              //协议内容分隔符   '*'
    private final byte sep_com = 0X2C;                                //协议内容分隔符   ','

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        // 记录包开始位置
        int thisBeginIndex = buffer.readerIndex();
        byte readied = buffer.readByte();
        // 读取到的不是开始标记，丢弃
        if(readied!= startData){
            buffer.discardReadBytes();
            return;
        }
        // 长度不够，返回等待下一次读取
        if (buffer.readableBytes() < BASE_LENGTH) {
            buffer.readerIndex(thisBeginIndex);
            return;
        }
        StringBuilder Key = new StringBuilder();
        StringBuilder DeviceInfo = new StringBuilder();
        StringBuilder LenStr = new StringBuilder();
        boolean lengthStrTrue = false;
        StringBuilder Command = new StringBuilder();
        boolean commandStrTrue = false;
        for (int i = 0; i < 34; i++) {
            Key.append((char) buffer.readByte());
        }
        System.out.println("Key: " + Key);
        if (buffer.readByte() != separator) {
            buffer.readerIndex(thisBeginIndex);
            return;
        }
        for (int i = 0; i < 15; i++) {
            DeviceInfo.append((char) buffer.readByte());
        }
        System.out.println("DeviceInfo: " + DeviceInfo);
        if (buffer.readByte() != separator) {
            buffer.readerIndex(thisBeginIndex);
            return;
        }
        for (int i = 0; i < 10; i++) {
            readied = buffer.readByte();
            if (readied == separator) {
                lengthStrTrue = true;
                break;
            }
            LenStr.append((char) readied);
        }
        if (!lengthStrTrue) {
            buffer.readerIndex(thisBeginIndex);
            return;
        }
        long Len = Long.parseLong(LenStr.toString(), 16);
        System.out.println("Len: " + Len);
        for (int i = 0; i < 10; i++) {
            readied = buffer.readByte();
            if (readied == sep_com || readied == endData) {
                commandStrTrue = true;
                break;
            }
            Command.append((char) readied);
        }
        if (!commandStrTrue) {
            buffer.readerIndex(thisBeginIndex);
            return;
        }
        System.out.println("Command: " + Command);
        String Com = Command.toString();
        P_Message p_message = new P_Message(Key.toString(), DeviceInfo.toString(), Len, Com);
        List<Byte> byteList = new ArrayList<>();
        long forLength;
        switch (Com) {
            case "TK":
            case "img":
                forLength = Len - Com.length() - 1;
                // 消息没有接收完成
                if(buffer.readableBytes()<forLength){
                    buffer.readerIndex(thisBeginIndex);
                    return;
                }
                for (long i = 0; i < forLength; i++) {
                    readied = buffer.readByte();
                    if (readied == endData) {
                        break;
                    }
                    if (readied == 0x7d && i < forLength - 2) {
                        byte readied2 = buffer.readByte();
                        i++;
                        switch (readied2) {
                            case 0X01:
                                byteList.add((byte) 0x7D);
                                break;
                            case 0X02:
                                byteList.add((byte) 0X5B);
                                break;
                            case 0X03:
                                byteList.add((byte) 0X5D);
                                break;
                            case 0X04:
                                byteList.add((byte) 0X2C);
                                break;
                            case 0X05:
                                byteList.add((byte) 0X2A);
                                break;
                            default:
                                byteList.add(readied);
                                byteList.add(readied2);
                                break;
                        }
                    } else {
                        byteList.add(readied);
                    }
                }
                break;
            default:
                if(buffer.readableBytes()>0){
                    forLength = Len - 1;
                    for (long i = 0; i < forLength; i++) {
                        readied = buffer.readByte();
                        if (readied == endData) {
                            break;
                        }
                        byteList.add(readied);
                    }
                }
                break;
        }
        Byte[] bytesT = new Byte[byteList.size()];
        byteList.toArray(bytesT);
        p_message.setContent_in(toPrimitives(bytesT));
        out.add(p_message);
        // 回收已读字节
        buffer.discardReadBytes();
    }

    private byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];
        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }

}
