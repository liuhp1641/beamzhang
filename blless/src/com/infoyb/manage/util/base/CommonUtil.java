package com.cm.manage.util.base;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class CommonUtil {

    public static String getPercent(Long x, Long total) {
        String result = "";//接受百分比的值
        if (total.longValue() != 0) {
            double tempresult = x / total;
            //NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法
            //nf.setMinimumFractionDigits( 2 );        保留到小数点后几位
            DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            //result=nf.format(tempresult);
            result = df1.format(tempresult);
        }
        return result;
    }

    public static boolean isNotEmpty(String str) {
        return (null != str && "".equals(str.trim()) == false);
    }

    public static boolean isNotEmpty(List list) {
        return (null != list && list.size() > 0);
    }

    public static boolean isNotEmpty(Object str) {
        return (null != str);
    }

    public static boolean isNotEmpty(StringBuffer str) {
        return (null != str && str.length() > 0);
    }

    public static boolean isNotEmptyObject(Object str) {
        return (null != str && !"".equals(str));
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static int formatInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static long formatLong(String str, int def) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static long formatLong(Long str, int def) {
        if (isNotEmpty(str)) {
            return str;
        } else {
            return def;
        }
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static Long formatLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static Integer formatInt(String str, Integer def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 转换字符串为浮点数
     *
     * @param str 待格式化字符串
     * @param def 默认值
     * @return
     */
    public static Float Double(String str, Float def) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 转换字符串为浮点数
     *
     * @param str 待格式化字符串
     * @param def 默认值
     * @return
     */
    public static Double formatDouble(String str, Double def) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static String formatDouble(Double value, String pattern) {
        if (pattern == null) {
            pattern = "#.##";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    /**
     * 转换字符串为浮点数
     *
     * @param str 待格式化字符串
     * @param def 默认值
     * @return
     */
    public static Double formatDouble(Double str, Double def) {
        if (isNotEmpty(str)) {
            return str;
        }
        return def;
    }

    /**
     * 转换科学计数法字符串为浮点数
     *
     * @param str 待格式化字符串
     * @return
     */
    public static String formatDouble(String str) {
        if (isNotEmpty(str)) {
            BigDecimal bd = new BigDecimal(str);
            return bd.toPlainString();
        }
        return "0";
    }

    /**
     * 转换BigDecimal为浮点数
     *
     * @param obj 待格式化字符串
     * @return
     */
    public static Double formatDouble(Object obj) {
        Double num = 0d;
        try {
            if (isNotEmpty(obj)) {
                BigDecimal db = (BigDecimal) obj;
                num = db.doubleValue();
            }
            return num;
        } catch (Exception e) {
            return num;
        }
    }
    
    public static double round(Object obj ,int scale) {  
    	Double num = 0d;
    	 try {
             if (isNotEmpty(obj)) {
            	 BigDecimal big = (BigDecimal) obj;
                 BigDecimal result = big.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);  
                 num = result.doubleValue();
             }
             return num;
         } catch (Exception e) {
             return num;
         }
    }

    /**
     * 去除号码两边的空格
     *
     * @param para    原字符串
     * @param defalut 如果为空默认值
     * @return
     */
    public static String formatStr(String para, String defalut) {
        if (null != para) {
            para = para.trim();
            if ("".equals(para)) {
                return defalut;
            }
            return para;
        } else {
            return defalut;
        }
    }

    /**
     * 去除号码两边的空格
     *
     * @param para 原字符串
     * @return
     */
    public static String formatStr(String para) {
        if (null != para) {
            para = para.trim();
            if ("".equals(para)) {
                return null;
            }
            return para;
        } else {
            return null;
        }
    }
    
    /**
    *
    * Description:将Clob对象转换为String对象,Blob处理方式与此相同
    *
    * @param clob
    * @return
    * @throws Exception
    * 
    */
   public static String oracleClob2Str(Clob clob) {
	   try{
	   String clobStr=clob != null ? clob.getSubString(1, (int) clob.length()) : null;
	   return clobStr;
	   }catch(Exception e){
		   e.printStackTrace();
	   }
       return null;
   }
   public static String random(int n) {
       if (n < 1 || n > 10) {
           throw new IllegalArgumentException("cannot random " + n + " bit number");
       }
       Random ran = new Random();
       if (n == 1) {
           return String.valueOf(ran.nextInt(10));
       }
       int bitField = 0;
       char[] chs = new char[n];
       for (int i = 0; i < n; i++) {
           while(true) {
               int k = ran.nextInt(10);
               if( (bitField & (1 << k)) == 0) {
                   bitField |= 1 << k;
                   chs[i] = (char)(k + '0');
                   break;
               }
           }
       }
       return new String(chs);
   }

   public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}    
	/***
	 * @describe 去除前后空格
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		 if(str == null){
			 return "";
		 }
		 return str.trim();
	 }

    public static String fullByZero(int in, int len) {
        String str = Integer.toString(in);
        if (null != str) {
            while (str.length() < len) {
                str = "0" + str;
            }
        }
        return str;
    }

    public static String fullByZero(String str, int len) {
        if (null != str) {
            while (str.length() < len) {
                str = "0" + str;
            }
        }
        return str;
    }   
    
    /**
   	 * 将String转成Clob ,静态方法
   	 * 
   	 * @param str
   	 *            字段
   	 * @return clob对象，如果出现错误，返回 null
   	 */
   	public static Clob stringToClob(String str) {
   		if (null == str)
   			return null;
   		else {
   			try {
   				java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str
   						.toCharArray());
   				return c;
   			} catch (Exception e) {
   				return null;
   			}
   		}
   	}

   	/**
   	 * 将Clob转成String ,静态方法
   	 * 
   	 * @param clob
   	 *            字段
   	 * @return 内容字串，如果出现错误，返回 null
   	 */
   	public static String clobToString(Clob clob) {
   		if (clob == null)
   			return null;
   		StringBuffer sb = new StringBuffer();
   		Reader clobStream = null;
   		try {
   			clobStream = clob.getCharacterStream();
   			char[] b = new char[60000];// 每次获取60K
   			int i = 0;
   			while ((i = clobStream.read(b)) != -1) {
   				sb.append(b, 0, i);
   			}
   		} catch (Exception ex) {
   			sb = null;
   		} finally {
   			try {
   				if (clobStream != null) {
   					clobStream.close();
   				}
   			} catch (Exception e) {
   			}
   		}
   		if (sb == null)
   			return null;
   		else
   			return sb.toString();
   	}
   	
   	public static String clobToString(oracle.sql.CLOB clob){
   		try{
   			Reader inStream = clob.getCharacterStream();
   	        char[] c = new char[(int) clob.length()];
   	        inStream.read(c);
   	        String data = new String(c);
   	        inStream.close();
   			return data;
   		}catch(Exception e){
   			e.printStackTrace();
   			return "";
   		}
   	}
}
