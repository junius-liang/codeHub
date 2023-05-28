package app.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author junius
 * @date 2023/05/09 20:21
 * @project codeHub
 * 客户端启动流程
 **/
public class Main_Test2 {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker).channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {

                    }
                });
        bootstrap.connect("localhost.com",8080).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("success");
            }else {
                System.out.println("error");
            }
        });
    }
}
