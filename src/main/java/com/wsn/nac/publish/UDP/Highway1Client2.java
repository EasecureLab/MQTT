package com.wsn.nac.publish.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Highway1Client2 {
    //查询电表数据
    public static String Ammetergetbackmsg(DatagramSocket socket, String datamsg)throws IOException {
        InetAddress address = UDPSupport.IPConvert("192.168.0.240");
        byte[] data1 = UDPSupport.getBufStrHex(datamsg);
        DatagramPacket packet1 = new DatagramPacket(data1, data1.length, address, 2000);
        socket.send(packet1);
        System.out.println("数据问询包发送成功");
        byte[] data2 = new byte[29];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        socket.receive(packet2);
        String reply = UDPSupport.getBufHexStr(packet2.getData());
        System.out.println(reply);
        return reply;
    }
}
