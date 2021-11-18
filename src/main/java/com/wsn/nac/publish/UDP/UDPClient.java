package com.wsn.nac.publish.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
public class UDPClient {
    //定义服务器的地址、端口号
    public static String IP = "192.168.0.240";
    public static int port = 2000;
        //public static String str = "192.168.0.3";
        //public static int port = 8080;
    public static void main(String[] args) throws IOException {

        //        不变：5A450201201100000000
//                时间戳：A7E700
//                不变：0000008000
//                查询电表数据：0304000000027029
//                距离计算：E4
//                结束：16
//        datamsg = "5A450201201100000000A7E70000000080000304000000027029E416";

        DatagramSocket socket = new DatagramSocket()  ;
        String datamsg ;
        //注册设备，并获取带有是时间戳的返回值
        // 02：源ID
        // AAAA：数据传输方式
        // 3333：上位机主/备注册
        // 7B: CkSum
        datamsg = "534E02AAFF16AAAA33337B16";
        String connectToMsg = Connect(socket,datamsg);


        int count = 1;
        //从连接到数据发送,获取电压数据

        // 生成获取电表数据的数据包
        datamsg = Highway1Support.AmmeterfromConnecttoMsg20Current(connectToMsg);
        // 获取返回的电表数据
        String fromAmmeter= Highway1Client.Ammetergetbackmsg(socket,datamsg);

        System.out.println("开始采集数据！");

        System.out.println("第"+count+"次："+"设备ID："+ Highway1Support.AmmetergetEquipmentID(fromAmmeter)+"——总线ID："+ Highway1Support.AmmetergetHighwayID(Integer.toString(port))
                +"——时间戳："+ Highway1Support.AmmetergetTime(fromAmmeter)+"——数据信息："
                +"湿度："+ Highway1Support.AmmetergetData(fromAmmeter)[0]/10+"%"
                + " 温度："+Highway1Support.AmmetergetData(fromAmmeter)[1]/10+"℃"+"——设备标签："+ Highway1Support.getTag());
        count++;
        while (true) {
            try {
                Thread.sleep(2000);

                datamsg = Highway1Support.AmmeterfromConnecttoMsg20Current(fromAmmeter);
                fromAmmeter = Highway1Client.Ammetergetbackmsg(socket, datamsg);

                System.out.println("第"+count+"次："+"设备ID："+ Highway1Support.AmmetergetEquipmentID(fromAmmeter)+"——总线ID："+ Highway1Support.AmmetergetHighwayID(Integer.toString(port))
                        +"——时间戳："+ Highway1Support.AmmetergetTime(fromAmmeter)+"——数据信息："
                        +"湿度："+ Highway1Support.AmmetergetData(fromAmmeter)[0]/10+"%"
                        + " 温度："+Highway1Support.AmmetergetData(fromAmmeter)[1]/10+"℃"+"——设备标签："+ Highway1Support.getTag());
                count++;
            } catch (Exception e) {
                System.out.println("失败:" + e);
            }
        }

    }


//连接函数
    public static String Connect(DatagramSocket socket,String datamsg) throws IOException{
        String back = new String();
        InetAddress address = UDPSupport.IPConvert(IP);
        byte[] data1 = UDPSupport.getBufStrHex(datamsg);
        DatagramPacket packet = new DatagramPacket(data1, data1.length, address, port);
        // 发包
        socket.send(packet);

        byte[] data2 = new byte[14];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        for (int i=0;i<1;i++)
        {
            try{
                // 收包
                socket.receive(packet2);
                String reply = UDPSupport.getBufHexStr(packet2.getData());
                System.out.println(reply);
                back = reply;
            }catch (Exception e){
                break;
            }
        }
        return back;
    }

}
 */
