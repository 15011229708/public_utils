package ejiang.online.publicutils.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanglin on 2018/5/8.
 */

public class StrUtil {


    //将 BASE64 编码的字符串 s 进行解码
    public static String getFromBASE64(String s) {
        if (s == null) return null;
        try {
            byte[] b = android.util.Base64.decode(s, android.util.Base64.DEFAULT);
            return new String(b, "utf-8");
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 验证是否是几位内的小数
     * @param str
     * @param num
     * @return
     */
    public static boolean isPerfectDecimal(String str, int num){
        if(!str.startsWith("0.")) {
            str = trimForeheadZero(str);
        }
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,"+num+"})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        return match.matches() != false;
    }

    /**
     * 去掉字符串前面的0
     * @param str
     * @return
     */
    public static String trimForeheadZero(String str) {
        return  str.replaceFirst("^0*", "");
    }

    /***
     * 验证是否是小数
     */
    public static boolean isDecimal(String dec) {
        return dec.contains(".") && dec.split("\\.").length == 2 && !dec.startsWith(".") && !dec.endsWith(".");
    }
    /***
     * 验证密码(6-20)
     */
    public static boolean verifyPwd(String pwd) {
        return null == pwd || "".equals(pwd) || pwd.length() < 6
                || pwd.length() > 20;
    }

    /**
     * @param str
     * @return验证是否是邮箱
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
//            String regex = "^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            String regex = "(?=^[\\w.@]{6,50}$)\\w+@\\w+(?:\\.[\\w]{2,3}){1,2}";
            return match(regex, str);
        }
    }

    /**
     * 手机号
     */
    public static boolean isPhone(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            String regex = "^(13|15|18|14|17|12|16|19)[0-9]\\d{8}$";
            return match(regex, str);
        }
    }


    /**
     * 描述：是否是中文.
     *
     * @param str 指定的字符串
     * @return 是否是中文:是为true，否则false
     */
    public static Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 数字或字母
     *
     * @param @param  str
     * @param @return
     * @return boolean
     * @throws
     * @Title: IsNumOrLetter
     * @Description: TODO
     */
    public static boolean isNumOrLetter(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            String regex = "^[A-Za-z0-9]{6}$";
            return match(regex, str);
        }
    }


    /**
     * 全数字
     *
     * @param @param  username
     * @param @return
     * @return boolean
     * @throws
     * @Title: IsAllNum
     * @Description: TODO
     */
    public static boolean IsAllNum(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            String regex = "\\d*";
            return match(regex, str);
        }
    }

    /**
     * @param @param  regex
     * @param @param  str
     * @param @return
     * @return boolean
     * @throws
     * @Title: match
     * @Description: TODO
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(Object input) {
        try {
            if (input == null || "".equals(input))
                return true;

            for (int i = 0; i < ((String)input).length(); i++) {
                char c = ((String)input).charAt(i);
                if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，false
     *
     * @param input
     * @return boolean
     */
    public static boolean isNotEmpty(Object input) {
        try {
            if (input == null || "".equals(input))
                return false;

            for (int i = 0; i < ((String)input).length(); i++) {
                char c = ((String)input).charAt(i);
                if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 获取四舍五入的价格字符串
     * @param price 价格
     */

    public static String getRoundedPriceZero(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(0, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }
    public static String getRoundedPriceOne(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }

    public static String getRoundedPriceTwo(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }

    public static String getRoundedPriceThree(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(3, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }

    public static String getRoundedPriceFour(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(4, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }

    public static String getRoundedPriceSix(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(6, BigDecimal.ROUND_HALF_UP);
//        System.out.println("getRoundedPriceSix: "+rounded.toString() );
        return rounded.toString();
    }



    public static String getRoundedPriceTwoPercent(double price) {
        BigDecimal decimal = new BigDecimal(price);
        BigDecimal rounded = decimal.setScale(4, BigDecimal.ROUND_HALF_UP);
        price = rounded.doubleValue();
        return price > 1.00 ? "100.00%" : getRoundedPriceTwo(price * 100)+"%";
    }

    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div(double value1,double value2,int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale).doubleValue();
    }

    /**
     * 判断身份证
     * @param IDNumber
     * @return
     */

    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(Date date) {
        // 得到当前时间的年、月、日
        if (date!=null){
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);
            //得到输入时间的年，月，日
            cal.setTime(date);
            int selectYear = cal.get(Calendar.YEAR);
            int selectMonth = cal.get(Calendar.MONTH) + 1;
            int selectDay =cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;// 先大致赋值
            if (yearMinus <=0) {
                age = 0;
            }if (monthMinus < 0) {
                age=age-1;
            } else if (monthMinus == 0) {
                if (dayMinus < 0) {
                    age=age-1;
                }
            }
            return age;
        }
        return 0;
    }


    /**
     * 判断值是不是空
     */
    public static Object convertObjectFromJson(JSONObject jsonData, String keyName, Object defaultValue){
        Object retValue = defaultValue;
        if (jsonData.has(keyName)){
            Object object = null;
            try {
                object = jsonData.get(keyName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!object.equals(null)&&object.toString().length()>0){
                retValue = object;
            }
        }
        return  retValue;
    }


    /***
     * 获取url 指定name的value;
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }

    public static Date getFetureDate(Integer past) {
        if (past == null) {
            past = 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + past);
        return calendar.getTime();
    }


    //除发
    public static String intDivision(int a, int b) {
        // TODO 自动生成的方法存根
        if(b==0){
            b=1;
        }
        BigDecimal aValue = BigDecimal.valueOf(Math.abs(a));
        BigDecimal bValue = BigDecimal.valueOf(Math.abs(b));
        BigDecimal resault = aValue.divide(bValue);
        return new DecimalFormat("#,##0.00").format(resault.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }
    //除发
    public static String intDivision1(int a, int b) {
        // TODO 自动生成的方法存根
        if(b==0){
            b=1;
        }
        BigDecimal aValue = BigDecimal.valueOf(Math.abs(a));
        BigDecimal bValue = BigDecimal.valueOf(Math.abs(b));
        BigDecimal resault = aValue.divide(bValue);
        return new DecimalFormat("##0.00").format(resault.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }
}
