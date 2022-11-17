package com.wsn.nac.publish.UDP;


/**
 * @author zwb
 * @date 2019/8/28 - 16:56
 **/
public class Highway1Support2 {

    //注册返回包生成电流数据发送包
    // string是注册的返回包
    public static String AmmeterfromConnecttoMsg20Current(String string){
        String msg = new String();
        String CkSum = "0201ff1100000000"+string.substring(20,26)+
                "0000008000"+"010300000002C40B";
        // String CkSum = "02011911" + "010300000002C40B";
        // string.substring(20,26)是时间信息
        // 030400080002F1EB：可能是请求电表数据的，之前的都是他的时间信息
        // 温度传感器的问询：010300000002C40B
        // 光照度传感器问询：010300060001640B
        // 上位机：5A4502011911000000000000000000008000010300000002C40B7216
        msg = msg+"5A450201ff1100000000"+string.substring(20,26)+
                "0000008000"+"010300000002C40B"+UDPSupport.getCkSum(CkSum)+"16";
        // msg = msg + "5A4502011911" + "010300000002C40B"+UDPSupport.getCkSum(CkSum)+"16";
        System.out.println(msg);
        return msg;
    }

    //设备ID——根据返回数据获得
    public static String AmmetergetEquipmentID(String string){
        if (string.length()>26){
            return string.substring(4,6);
        }
        return "未获得ID！";
    }

    //总线ID——根据返回数据获得
    public static String AmmetergetHighwayID(String string){
        return UDPSupport.portToHighwayID(string);
    }

    //时间戳——根据返回数据获得
    public static String AmmetergetTime(String string){
        if (string.length()>26){
            return string.substring(20,26);
        }
        return "未获得时间戳！";
    }

    //IEEE754协议解析电表数据
    public static float[] AmmetergetData(String string){
        // 返回温湿度数据示例：01030401E6FF9F1BA0
        //byte[] fromData;
        // 温度传感器的数据处理

        String str;
        String tempStr;
        String humStr;
        float[] datas = new float[2];
        if (string.length()>26){
            // fromData = UDPSupport.getBufStrHex(string.substring(42,50));
            str = string.substring(36,54);
            humStr = str.substring(6,10);
            tempStr = str.substring(10,14);
            float temp = hexToTen(tempStr);
            // System.out.println(temp/10 + "摄氏度");
            float hum = hexToTen(humStr);
            // System.out.println(hum/10 + "%");
            datas[0] = hum;
            datas[1] = temp;
            /**
             int bits = fromData[3]&0xff|(fromData[2]&0xff)<<8|(fromData[1]&0xff)<<16|(fromData[0]&0xff)<<24;
             int sign = ((bits&0x80000000)==0)?1:-1;
             int exp = ((bits & 0x7f800000)>>23);
             int man = (bits & 0x007fffff);

             man |= 0x00800000;

             float f = (float)(sign*man*Math.pow(2,exp - 150));
             return Float.toString(f);
             */

            return datas;
        }
        return null;
        /**
         * 光照度传感器部分代码
         String str;
         String tempStr;
         if (string.length()>26) {
         // fromData = UDPSupport.getBufStrHex(string.substring(42,50));
         str = string.substring(36, 54);
         tempStr = str.substring(6, 10);
         float temp = hexToTen(tempStr);

         return temp;
         }
         return null;
         */

    }

    public static String Tag;
    //存储设备标签
    public static void setTag(String tag) {
        Tag = tag;
    }
    //获取设备标签
    public static String getTag() {
        Tag = "温湿度传感器";
        return Tag;
    }
    public static float hexToTen(String string){
        float sum = 0;
        for(int i=0;i<string.length();i++)
        {
            int m=string.charAt(i);//将输入的十六进制字符串转化为单个字符
            int num=m>='A'?m-'A'+10:m-'0';//将字符对应的ASCII值转为数值
            sum+=Math.pow(16, string.length()-1-i)*num;
        }
        return sum;
    }
}
