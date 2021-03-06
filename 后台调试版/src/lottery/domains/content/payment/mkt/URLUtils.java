package lottery.domains.content.payment.mkt;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;

public class URLUtils
{
  public static String encode(String str, String charset)
  {
    try
    {
      return URLEncoder.encode(str, charset);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return str;
  }
  
  public static String decode(String str, String charset)
  {
    try
    {
      return URLDecoder.decode(str, charset);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return str;
  }
  
  public static void appendParam(StringBuilder sb, String name, String val)
  {
    appendParam(sb, name, val, true);
  }
  
  public static void appendParam(StringBuilder sb, String name, String val, String charset)
  {
    appendParam(sb, name, val, true, charset);
  }
  
  public static void appendParam(StringBuilder sb, String name, String val, boolean and)
  {
    appendParam(sb, name, val, and, null);
  }
  
  public static void appendParam(StringBuilder sb, String name, String val, boolean and, String charset)
  {
    if (and) {
      sb.append("&");
    } else {
      sb.append("?");
    }
    sb.append(name);
    sb.append("=");
    if (val == null) {
      val = "";
    }
    if (StringUtils.isEmpty(charset)) {
      sb.append(val);
    } else {
      sb.append(encode(val, charset));
    }
  }
}
