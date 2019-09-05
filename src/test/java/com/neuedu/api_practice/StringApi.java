package com.neuedu.api_practice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StringApi {

    /**
     *


     ————————————————
     版权声明：本文为CSDN博主「jingjing_78495」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/weixin_38648597/article/details/78952094
     * */
    public static void main(String[] args) {

        /**
         * 1、equals()：比较两个字符串是否相等
         它具有如下的一般形式：boolean equals(Object str)
         str是一个用来与调用字符串（String）对象做比较的字符串（String）对象。
         如果两个字符串具有相同的字符和长度，它返回true，否则返回false。这种比较是区分大小写的。
         * */

         String str1 = "abcd";
         int str2 = 1234;
         String str3 = "aBcD";
         System.out.println(str1.equals(str2));

         /**
          * 2、equalsIgnoreCase( )：忽略大小写的两个字符串是否相等比较
          当比较两个字符串时，它会认为A-Z和a-z是一样的。
          其一般形式如下：boolean equalsIgnoreCase(String str)
          str是一个用来与调用字符串（String）对象做比较的字符串（String）对象。
          如果两个字符串具有相同的字符和长度，它也返回true，否则返回false。
          * */
        System.out.println(str1.equalsIgnoreCase(str3));

        /**
         * 3、toString()：转换成String类型
         Object object = getObject();
         System.out.println(object.toString());
         注意：必须保证object不是null值，否则将抛出NullPointerException异常。
         采用这种方法时，通常派生类会覆盖Object里的toString（）方法。
         * */

        /**
         * 只有引用数据类型可以用toString()方法，基本数据类型无法toString();
         * 基本数据类型一般有String.valueOf(),如果非要用toString()方法的话，需要转成对应的包装类;
         * 基本数据+””的效率最慢
         * */


        Person person = new Person("zhangsan","123456");
        System.out.println(person.toString());
        System.out.println(String.valueOf(str2));

        /**
         * 4、String：转换成String类型
         （String）object：这是标准的类型转换，将object转成String类型的值。
         注意：类型必须能转成String类型。因此最好用instanceof做个类型检查，以判断是否可以转换。否则容易抛出CalssCastException异常。
         因定义为Object 类型的对象在转成String时语法检查并不会报错，这将可能导致潜在的错误存在。
         如： Object obj = new Integer(100);
         String strVal = (String)obj;
         在运行时将会出错，因为将Integer类型强制转换为String类型，无法通过。
         但是， Integer obj = new Integer(100);
         String strVal = (String)obj;
         如是格式代码，将会报语法错误。此外，因null值可以强制转换为任何java类类型，(String)null也是合法的。
         * */

        Object num = null;
        boolean result = num instanceof Object;
        System.out.println(result+"=======");
        String string = (String)num;
        System.out.println(string);

        /**
         *  5、String.valueOf()：转换成String类型（不用担心object是否为null值这一问题）
         注意：当object为null 时，String.valueOf（object）的值是字符串”null”，而不是null。
         * */
        System.out.println(String.valueOf(num));

        /**
         * 6、split()：分隔符
         1、如果用“.”作为分隔的话,必须是如下写法,String.split("\\.")
         2、如果用“|”作为分隔的话,必须是如下写法,String.split("\\|")
         “.”、“|”、"*" 和"+"都是转义字符,必须得加"\\";
         3、如果在一个字符串中有多个分隔符,可以用“|”作为连字符,比如,“acount=? and uu =? or n=?”,把三个都分隔出来,可以用String.split("and|or");
         例如：String[] aa = "aaa|bbb|ccc".split("\\*");
         for (int i = 0 ; i <aa.length ; i++ ) {
         System.out.println("--"+aa[i]);
         }
         * */

        String webSite = "www.baidu.com";
        String[] split = webSite.split("\\.");
        List list = new ArrayList<>();
       for (String result1:split){
           list.add(result1);
           System.out.print(result1);
       }
        System.out.println(list);

       /**
        * 7、subString()：截取字符串中的一段字符串
        String str;
        （1）str＝str.substring(int beginIndex);
        截取掉str从首字母起长度为beginIndex的字符串，将剩余字符串赋值给str；
        （2）str＝str.substring(int beginIndex，int endIndex);
        截取str中从beginIndex开始至endIndex结束时的字符串，并将其赋值给str;
        * */

       /**
        * 前闭后开原则
        * */
        System.out.println(webSite.substring(1,8));

        /**
         * 8、charAt()：返回指定索引处char值
         public char charAt(int index)
         char s = str.charAt(1);
         * */
        System.out.println(webSite.charAt(3));

        /**
         *  9、toLowerCase()：将所有在此字符串中的字符转化为小写（使用默认语言环境的规则）
         public String toLowerCase()
         String newStr = str.toLowerCase();
         * */

        String upLower = "ASDFDSGFDFG";
        String newString = upLower.toLowerCase();
        System.out.println(newString);

        /**
         *      10、indexOf()：指出 String 对象内子字符串的开始位置
         1、int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。
         2、int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。
         3、int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。
         4、int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引。
         注意：如果没有找到子字符串，则返回-1。
         如果 startindex 是负数，则 startindex 被当作零。如果它比最大的字符位置索引还大，则它被当作最大的可能索引。
         例如：String s = "xXccxxxXX";
         // 从头开始查找是否存在指定的字符 //结果如下
         System.out.println(s.indexOf("c")); //2
         // 从第四个字符位置开始往后继续查找，包含当前位置
         System.out.println(s.indexOf("c", 3)); //3
         //若指定字符串中没有该字符则系统返回-1
         System.out.println(s.indexOf("y")); //-1
         System.out.println(s.lastIndexOf("x")); //6
         * */

        /**
         * charAt()和indexOf()是对应关系，
         * charAt()是根据索引找指定字符;
         * indexOf()是根据指定字符串找索引
         * */

        String childStr = "asdfsdfsdddd";
        int sd = childStr.indexOf("fs",3);
        System.out.println(sd);
        int fs = childStr.lastIndexOf("fs",10);
        System.out.println(fs);

        /**
         * 11、replace和replaceAll
         （1）replace的参数是char和CharSequence，即可以支持字符的替换，也支持字符串的替换（CharSequence即字符串序列的意思,说白了也就是字符串）；
         （2）replaceAll的参数是regex，即基于规则表达式的替换，比如：可以通过replaceAll("\\d", "*")把一个字符串所有的数字字符都换成星号；
         相同点：都是全部替换，即把源字符串中的某一字符或字符串全部换成指定的字符或字符串；
         不同点：（1）replaceAll支持正则表达式，因此会对参数进行解析（两个参数均是），如replaceAll("\\d", "*")，而replace则不会，replace("\\d","*")就是替换"\\d"的字符串，而不会解析为正则。
         （2）“\”在java中是一个转义字符，所以需要用两个代表一个。例如System.out.println( "\\" ) ;只打印出一个"\"。但是“\”也是正则表达式中的转义字符，需要用两个代表一个。所以：\\\\被java转换成\\，\\又被正则表达式转换成\，因此用replaceAll替换“\”为"\\"，就要用replaceAll("\\\\","\\\\\\\\")，而replace则replace("\\","\\\\")。
         （3）如果只想替换第一次出现的，可以使用replaceFirst()，这个方法也是基于规则表达式的替换，但与replaceAll()不同的是，只替换第一次出现的字符串。
         说到正则表达式，有个例子就能很好的解释replaceAll的用法。即替换空格
         String test = "wa n\tg_p\\te\\tn　g";
         test = test.replaceAll("\\t|\\\\t|\u0020|\\u3000", "");//去掉空格
         System.out.println(test);
         其中test = test.replaceAll("\\t|\\\\t|\u0020|\\u3000", "")
         与test = Pattern.compile("\\t|\\\\t|\u0020|\\u3000").matcher(test).replaceAll("")
         是等效的，
         因此用正则表达式仅仅是替换全部或替换第一个的话，用replaceAll或replaceFirst即可。
         * */

        String username = "zhangsan";
        String a = username.replace("a", "*");
        System.out.println(a);

        /**
         * 12、getBytes()：得到一个系统默认的编码格式的字节数组
         都是将一个string类型的字符串转换成byte类型并且存入一个byte数组中。在java中的所有数据底层都是字节，字节数据可以存入到byte数组。
         UTF-8每个汉字转成3bytes，而GBK转成2bytes，所以要说明编码方式，否则用缺省编码。
         String.getBytes(String decode)
         byte[] b_gbk = "中".getBytes("GBK");
         byte[] b_utf8 = "中".getBytes("UTF-8");
         byte[] b_iso88591 = "中".getBytes("ISO8859-1");
         将分别返回"中"这个汉字在GBK、UTF-8和ISO8859-1编码下的byte数组表示,此时
         b_gbk的长度为2,
         b_utf8的长度为3,
         b_iso88591的长度为1。
         new String(byte[], decode)实际是使用指定的编码decode来将byte[]解析成字符串.
         String s_gbk = new String(b_gbk,"GBK");
         String s_utf8 = new String(b_utf8,"UTF-8");
         String s_iso88591 = new String(b_iso88591,"ISO8859-1");
         通过输出s_gbk、s_utf8和s_iso88591,会发现s_gbk和s_utf8都是"中",而只有s_iso88591是一个不被识别的字符（可以理解为乱码）,为什么使用ISO8859-1编码再组合之后,无法还原"中"字？原因很简单,因为ISO8859-1编码的编码表根本就不包含汉字字符,当然也就无法通过"中".getBytes("ISO8859-1");来得到正确的"中"字在ISO8859-1中的编码值了,所以，再通过new String()来还原就更是无从谈起。因此,通过String.getBytes(String decode)方法来得到byte[]时,一定要确定decode的编码表中确实存在String表示的码值,这样得到的byte[]数组才能正确被还原。
         * */

        byte[] bytes = str1.getBytes();
        for (byte item:bytes){
            System.out.println(item);
        }

        /**
         * 13、StringBuffer的append方法
         StringBuffer buf=new StringBuffer("Hard ");
         String aString = "Waxworks";
         buf.append(aString,3,4);
         原文说明：这个操作将aString的从索引位置3开始的由四个字符组成的子串追加到StringBuffer对象buf中。然后buf对象就会包含字符 串"Hard work"。
         请注意，这个代码的实际运行结果是： buf对象包含的字符串为"Hard w"。
         具体原因引用源代码：
         public synchronized StringBuffer append(CharSequence s, int start, int end)
         {
         super.append(s, start, end);
         return this;
         }
         根据运行结果分析，StringBuffer对象的append（）方法的参数，如果是String类型，那么，后面取子串的操作实际是从索引3开始，取值到索引4之前的串。如果append的语句改成 buf.append(aString,3,3); ,那么没有添加aString的子串，即 buf包含的字符实际还是"Hard "。如果此语句再改成 buf.append(aString3,2); ，那么系统会抛出"IndexOutOfBoundsException"的异常！
         但是，如果append()的参数是字符数组（char[])，那么结果就如原文所述，buf将包含串"Hard work". 代码如下：
         StringBuffer buf=new StringBuffer("Hard ");
         char[] text ={'W','a','x','w','o','r','k','s'};
         buf.append(text ,3,4); // buf包含串"Hard work"
         具体原因引用源代码：
         public synchronized StringBuffer append(char str[], int offset, int len)
         {
         super.append(str, offset, len);
         return this;
         }
         JAVA 中 Stringbuffer 有append()方法
         　　Stringbuffer其实是动态字符串数组
         　　append()是往动态字符串数组添加，跟“xxxx”+“yyyy”相当那个‘+’号
         　　跟String不同的是Stringbuffer是放一起的
         　　String1+String2 和Stringbuffer1.append("yyyy")虽然打印效果一样，但在内存中表示却不一样
         　　String1+String2 存在于不同的两个地址内存
         　　Stringbuffer1.append(Stringbuffer2)放再一起
         * */

        StringBuffer s = new StringBuffer("Hard");
        String ss = "aaaworks";
        StringBuffer append = s.append(ss, 3, 4);
        System.out.println(append);


        /**
         * format
         *
         * simpleDateFormat
         * */

        long l = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    }
}
