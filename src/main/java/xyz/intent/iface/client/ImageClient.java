package xyz.intent.iface.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * main.java.iface.client
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/9/16 21:59
 * @since 1.0
 */
public class ImageClient {
    private static final Logger logger = LoggerFactory.getLogger(ImageClient.class);

    private String ip;
    private int port;
    private ImageClientHandler imageClientHandler;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ImageClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("logging", new LoggingHandler());
                            imageClientHandler = new ImageClientHandler();
                            p.addLast("handler", imageClientHandler);
                        }
                    });
            ChannelFuture f;
            f = bootstrap.connect(new InetSocketAddress(getIp(), getPort())).sync();
            f.channel().closeFuture().sync();
            f.addListener(future -> {
                if (!future.isSuccess()) {
                    logger.error("{}:{} connection fail!", getIp(), getPort());
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public void requestImage() {
        imageClientHandler.send(1);
    }

    public void sendData(byte[] data) {
        imageClientHandler.send(data);
    }
}
