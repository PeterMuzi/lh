package com.huhui.util.encrypt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Hex;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/*
 * mt4 manager登录账户的加密解密
 */
public class Mt4DesDe {

	public static void main(String[] args) {
        System.out.println(getAsc(""));
        System.out.println(backchar(31));
        System.out.println(11%10);
                         
        System.out.println(getDecString("1+2","753235"));//
    }

    /**
     * 字符转ASC
     * 
     * @param st
     * @return
     */
    public static int getAsc(String st) {
        byte[] gc = st.getBytes();
        int ascNum = (int) gc[0];
        return ascNum;
    }

    /**
     * ASC转字符
     * 
     * @param backnum
     * @return
     */
    public static char backchar(int backnum) {
        char strChar = (char) backnum;
        return strChar;
    }
	
	public static String getDecString(String str,String key)
	{
		char[] cs=str.toCharArray();
		int[] keyAscII=toAscII( key);
		String res="";
		for(int i=0;i<str.length();i++){
			char c=cs[i];
			int asc=getAsc(String.valueOf(c));
			int mo=i%keyAscII.length;
			int v=keyAscII[mo];
			asc=asc+v;
			res=res+String.valueOf(backchar(asc));
		}
		return res;
	}
	
	public static int[] toAscII(String key)
	{
		int[] res=new int[key.length()];
		char[] cs=key.toCharArray();
		for(int i=0;i<cs.length;i++){
			char c=cs[i];
			res[i]=Integer.parseInt(String.valueOf(c));
		}
		return res;
	}
/*
33 !
34 "
35 #
36 $
37 %
38 &
39 '
40 (
41 )
42 *
43 +
44 ,
45 -
46 .
47 /
48 0
49 1
50 2
51 3
52 4
53 5
54 6
55 7
56 8
57 9
58 :
59 ;
60 <
61 =
62 >
63 ?
64 @
65 A
66 B
67 C
68 D
69 E
70 F
71 G
72 H
73 I
74 J
75 K
76 L
77 M
78 N
79 O
80 P
81 Q
82 R
83 S
84 T
85 U
86 V
87 W
88 X
89 Y
90 Z
91 [
92 \
93 ]
94 ^
95 _
96 `
97 a
98 b
99 c
100 d
101 e
102 f
103 g
104 h
105 i
106 j
107 k
108 l
109 m
110 n
111 o
112 p
113 q
114 r
115 s
116 t
117 u
118 v
119 w
120 x
121 y
122 z
123 {
124 |
125 }
126 ~
	 */
}
