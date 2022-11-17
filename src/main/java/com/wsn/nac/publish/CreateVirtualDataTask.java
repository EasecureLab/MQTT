// package com.wsn.nac.publish;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.wsn.nac.publish.UDP.Highway1Client;
// import com.wsn.nac.publish.UDP.Highway1Support;
// import com.wsn.nac.publish.UDP.UDPSupport;
// import com.wsn.nac.publish.service.PushService;
// import com.wsn.nac.publish.entity.*;
// import lombok.RequiredArgsConstructor;
// import lombok.SneakyThrows;
// import org.quartz.JobExecutionContext;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.quartz.QuartzJobBean;
// import org.springframework.stereotype.Component;
//
// import java.io.IOException;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import java.util.Date;
//
//
// @Component
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
// public class CreateVirtualDataTask extends QuartzJobBean {
//
//    final private PushService push;
//
//    public static String IP = "192.168.0.240";
//    public static int port = 2000;
//
//    @SneakyThrows
//    @Override
//    public void executeInternal(JobExecutionContext jobExecutionContext)  {
//
//        DatagramSocket socket = new DatagramSocket();
//        String datamsg;
//
//        datamsg = "534E02AAFF16AAAA33337B16";
//
//
//        String connectToMsg = Connect(socket,datamsg);
//
//        // 生成获取温湿度数据的数据包
//        datamsg = Highway1Support.AmmeterfromConnecttoMsg20Current(connectToMsg);
//        // 获取返回的电表数据
//        String fromAmmeter= Highway1Client.Ammetergetbackmsg(socket,datamsg);
//
//        pushBody body = new pushBody();
//        temperature temp = new temperature();
//
//        temp.setTempData(Highway1Support.AmmetergetData(fromAmmeter)[1]/10);
//                //湿度的单位是%
//        temp.setHumData(Highway1Support.AmmetergetData(fromAmmeter)[0]/10);
//        temp.setDeviceId("1");
//        temp.setDateTime(new Date());
//        temp.setSensorType("temperature");
//
//        System.out.println("开始产生数据：");
//        System.out.println(temp);
//
//        body.setTopics("temperature");
//        body.setQos(2);
//        body.setPayload(new ObjectMapper().writeValueAsString(temp));
//        body.setRetain(false);
//        body.setClientid("sendSensorData");
// //            System.out.println(temp.toString());
//        Thread.sleep(500);
//        push.pushToBroker(body);
//        System.out.println("发送到MQTT broker成功！");
//
//    }
//
//    //连接函数
//    public static String Connect(DatagramSocket socket,String datamsg) throws IOException {
//        String back = new String();
//        InetAddress address = UDPSupport.IPConvert(IP);
//        byte[] data1 = UDPSupport.getBufStrHex(datamsg);
//        DatagramPacket packet = new DatagramPacket(data1, data1.length, address, port);
//        // 发包
//        socket.send(packet);
//
//        byte[] data2 = new byte[14];
//        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
//        for (int i=0;i<1;i++)
//        {
//            try{
//                // 收包
//                socket.receive(packet2);
//                String reply = UDPSupport.getBufHexStr(packet2.getData());
//                // System.out.println(reply);
//                back = reply;
//            }catch (Exception e){
//                break;
//            }
//        }
//        return back;
//    }
// }
