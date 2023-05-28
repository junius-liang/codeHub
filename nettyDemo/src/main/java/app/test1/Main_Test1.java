package app.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author junius
 * @date 2023/05/09 19:46
 * @project codeHub
 * 服务端启动流程
 **/
public class Main_Test1 {

    public static void main(String[] args) {
        /*
        两个NioEventLoopGroup，这两个对象可以看作传统IO编程模型的两大线程组，bossGroup表示监听端口，接收新连接的线程组；
        workerGroup表示处理每一个连接的数据读写的线程组
         */
        NioEventLoopGroup woker = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        /*
        创建了一个引导类ServerBootstrap，这个类将引导服务端的启动工作。
         */
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                //通过.group(bossGroup,workerGroup)给引导类配置两大线程组，这个引导类的线程模型也就定型了。
                .group(boss, woker)
                //然后指定服务端的IO模型为NIO，上述代码通过.channel(NioServerSocketChannel.class)来指定IO模型，也可以有其他选择。
                .channel(NioServerSocketChannel.class)
                //接着调用childHandler()方法，给这个引导类创建一个ChannelInitializer，主要是定义后续每个连接的数据读写
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("..............");
                    }
                });
        bind(bootstrap,8080);

    }

    public static void bind(final ServerBootstrap bootstrap,final int port){
        bootstrap
                .bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()){
                            System.out.println("success:"+port);
                        }else {
                            System.out.println("fail");
                            bind(bootstrap,port+1);
                        }
                    }
                });
    }
}
