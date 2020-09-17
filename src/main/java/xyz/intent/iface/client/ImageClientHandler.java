package xyz.intent.iface.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xyz.intent.iface.util.ByteUtils;

/**
 * main.java.iface.client
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/9/16 22:08
 * @since 1.0
 */
public class ImageClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("打开socket: " + ctx.channel().remoteAddress());
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
            throws Exception {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        System.out.println("msg: " + new String(data));
        ctx.writeAndFlush(Unpooled.copiedBuffer(data));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void send(int i) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(ByteUtils.intToBytesBigEndian(i)));
    }

    public void send(byte[] data) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(data));
    }
}
