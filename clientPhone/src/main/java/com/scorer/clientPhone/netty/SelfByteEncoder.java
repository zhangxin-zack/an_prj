package com.scorer.clientPhone.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.UnsupportedEncodingException;

public class SelfByteEncoder extends MessageToByteEncoder<P_Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, P_Message p_message, ByteBuf out) throws UnsupportedEncodingException {
        String w_content = GetWriteContent(p_message);
        byte[] w_content_byte = w_content.getBytes("GB2312");
        byte[] outBack = new byte[w_content_byte.length + (p_message.getContent_out() == null ? 0 : p_message.getContent_out().length) + 1];
        System.arraycopy(w_content_byte, 0, outBack, 0, w_content_byte.length);
        if(p_message.getContent_out() != null){
            System.arraycopy(p_message.getContent_out(), 0, outBack, w_content_byte.length, p_message.getContent_out().length);
        }
        outBack[outBack.length-1]=']';
        // 写入消息主体
        out.writeBytes(outBack);
        System.out.println("OUT-> "+new String(outBack,"GB2312"));
    }

    private String GetWriteContent(P_Message p_message) {
        String r = "";
        r += "[";
        r += p_message.getKey();
        r += "*";
        r += p_message.getRing_no();
        r += "*";
        String LenBack = Long.toHexString(p_message.getLen()).toUpperCase();
        LenBack = LenBack.length() > 4 ? LenBack : ("0000" + LenBack).substring(("0000" + LenBack).length() - 4);
        r += LenBack;
        r += "*";
        r += p_message.getCommand();
        if (p_message.getContent_out() != null && p_message.getContent_out().length > 0) {
            r += ",";
        }
        return r;
    }

}
