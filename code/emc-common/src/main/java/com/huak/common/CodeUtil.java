package com.huak.common;

import sun.security.action.GetPropertyAction;

import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.AccessController;
import java.util.BitSet;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.common<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-02<BR>
 * Description:  编码转码
 *  * "\\zhong中文!@#~$%^&*()_+"
 * %5c%5czhong%e4%b8%ad%e6%96%87!@#~$%25%5e&*()_+
 * %5C%5Czhong%E4%B8%AD%E6%96%87!@#~$%25%5E&*()_+
 * <BR>
 * Function List:  <BR>
 */
public class CodeUtil {

    static BitSet dontNeedEncoding;
    static final int caseDiff = ('a' - 'A');
    static String dfltEncName = null;
    static {

        /* The list of characters that are not encoded has been
         * determined as follows:
         *
         * RFC 2396 states:
         * -----
         * Data characters that are allowed in a URI but do not have a
         * reserved purpose are called unreserved.  These include upper
         * and lower case letters, decimal digits, and a limited set of
         * punctuation marks and symbols.
         *
         * unreserved  = alphanum | mark
         *
         * mark        = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
         *
         * Unreserved characters can be escaped without changing the
         * semantics of the URI, but this should not be done unless the
         * URI is being used in a context that does not allow the
         * unescaped character to appear.
         * -----
         *
         * It appears that both Netscape and Internet Explorer escape
         * all special characters from this list with the exception
         * of "-", "_", ".", "*". While it is not clear why they are
         * escaping the other characters, perhaps it is safest to
         * assume that there might be contexts in which the others
         * are unsafe if not escaped. Therefore, we will use the same
         * list. It is also noteworthy that this is consistent with
         * O'Reilly's "HTML: The Definitive Guide" (page 164).
         *
         * As a last note, Intenet Explorer does not encode the "@"
         * character which is clearly not unreserved according to the
         * RFC. We are being consistent with the RFC in this matter,
         * as is Netscape.
         *
         */

        dontNeedEncoding = new BitSet(256);
        int i;
//        for (i = 'a'; i <= 'z'; i++) {
//            dontNeedEncoding.set(i);
//        }
//        for (i = 'A'; i <= 'Z'; i++) {
//            dontNeedEncoding.set(i);
//        }
        dontNeedEncoding.set(' '); /* encoding a space to a + is done
                                    * in the encode() method */
        dontNeedEncoding.set('-');
        dontNeedEncoding.set('_');
        dontNeedEncoding.set('*');
        dontNeedEncoding.set('!');
        dontNeedEncoding.set('~');

        dfltEncName = AccessController.doPrivileged(
                new GetPropertyAction("file.encoding")
        );
    }

    public static String encodeURIComponent(String s, String enc)
            throws UnsupportedEncodingException {

        boolean needToChange = false;
        StringBuffer out = new StringBuffer(s.length());
        Charset charset;
        CharArrayWriter charArrayWriter = new CharArrayWriter();

        if (enc == null)
            throw new NullPointerException("charsetName");

        try {
            charset = Charset.forName(enc);
        } catch (IllegalCharsetNameException e) {
            throw new UnsupportedEncodingException(enc);
        } catch (UnsupportedCharsetException e) {
            throw new UnsupportedEncodingException(enc);
        }

        for (int i = 0; i < s.length();) {
            int c = (int) s.charAt(i);
            //System.out.println("Examining character: " + c);
            if (dontNeedEncoding.get(c)) {
                if (c == ' ') {
                    c = '+';
                    needToChange = true;
                }
                //System.out.println("Storing: " + c);
                out.append((char)c);
                i++;
            } else {
                // convert to external encoding before hex conversion
                do {
                    charArrayWriter.write(c);
                    /*
                     * If this character represents the start of a Unicode
                     * surrogate pair, then pass in two characters. It's not
                     * clear what should be done if a bytes reserved in the
                     * surrogate pairs range occurs outside of a legal
                     * surrogate pair. For now, just treat it as if it were
                     * any other character.
                     */
                    if (c >= 0xD800 && c <= 0xDBFF) {
                        /*
                          System.out.println(Integer.toHexString(c)
                          + " is high surrogate");
                        */
                        if ( (i+1) < s.length()) {
                            int d = (int) s.charAt(i+1);
                            /*
                              System.out.println("\tExamining "
                              + Integer.toHexString(d));
                            */
                            if (d >= 0xDC00 && d <= 0xDFFF) {
                                /*
                                  System.out.println("\t"
                                  + Integer.toHexString(d)
                                  + " is low surrogate");
                                */
                                charArrayWriter.write(d);
                                i++;
                            }
                        }
                    }
                    i++;
                } while (i < s.length() && !dontNeedEncoding.get((c = (int) s.charAt(i))));

                charArrayWriter.flush();
                String str = new String(charArrayWriter.toCharArray());
                byte[] ba = str.getBytes(charset);
                for (int j = 0; j < ba.length; j++) {
                    out.append('%');
                    char ch = Character.forDigit((ba[j] >> 4) & 0xF, 16);
                    // converting to use uppercase letter as part of
                    // the hex value if ch is a letter.
                    if (Character.isLetter(ch)) {
                        ch -= caseDiff;
                    }
                    out.append(ch);
                    ch = Character.forDigit(ba[j] & 0xF, 16);
                    if (Character.isLetter(ch)) {
                        ch -= caseDiff;
                    }
                    out.append(ch);
                }
                charArrayWriter.reset();
                needToChange = true;
            }
        }

        return (needToChange? out.toString() : s);
    }
    public static void main(String args[]) throws UnsupportedEncodingException {
        // String str = "\\zhong中文!@#~$%^&*()_+";
//        String str = "http://localhost/qq/index.jsp?title=专业";
//        System.out.println(encodeURI(str));
//        System.out.println(URLEncoder.encode(str, "UTF8"));
//        System.out.println(CodeUtil.encodeURIComponent(str));

        System.out.println(CodeUtil.encodeURIComponent("流程图演示.流程演示1","GBK"));
        String uri = "zzrl.九四家属院换热站";
        System.out.println(CodeUtil.encodeURIComponent(uri,"GBK"));
        //System.out.println(CodeUtil.encodeURIComponent(".1","GBK"));

        // http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.
        // http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.
        // http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.
        // http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.
        // http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.

        String u2= "http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%2Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%3D%3D&spm=2014.12504724.";

        // http%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3DzGU34CA7K%252BPkqB07S4%252FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%252Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%253D%253D%26spm%3D2014.12504724.
        // http%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3DzGU34CA7K%252BPkqB07S4%252FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%252Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%253D%253D%26spm%3D2014.12504724.
        // http%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3DzGU34CA7K%252BPkqB07S4%252FK0CFcRfH0G7DbPkiN9MMNCgholtIFKpfrSW%252Fzxy2y7fekDuLWp7ZKc3WEFupJCwtNI8iERf3ngtEgSbkBG5JPsEOA83kHPjbuUZp0ropQmTrG6mOLw2IhEjTldtOgszXvghs67sQqqxszKv8gdygH8eOb5XfQ43m2JcfktZn0FNGe4w%253D%253D%26spm%3D2014.12504724.
//        String eu2 = encodeURIComponent(u2);
//
//        System.out.println(u2);
//        System.out.println(eu2);
//        System.out.println(decodeURIComponent(eu2));
//        System.out.println(decodeURIComponent(u2));

    }
}

