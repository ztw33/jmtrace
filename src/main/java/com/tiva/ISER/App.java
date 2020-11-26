package com.tiva.ISER;

/**
 * Hello world!
 *
 */
public class App
{
    public boolean[] az = new boolean[1];
    public char[] ac = new char[1];
    public byte[] ab = new byte[1];
    public short[] as = new short[1];
    public int[] ai = new int[1];
    public long[] al = new long[1];
    public float[] af = new float[1];
    public double[] ad = new double[1];
    public String[] astr = new String[1];

    public static boolean sz;
    public static char sc;
    public static byte sb;
    public static short ss;
    public static int si;
    public static long sl;
    public static float sf;
    public static double sd;
    public static String sstr;

    public static void main(String[] args) {
        App.sz = true;
        App.sc = 'a';
        App.sb = 1;
        App.ss = 2;
        App.si = 3;
        App.sl = 4;
        App.sf = 5.0f;
        App.sd = 6.0;
        App.sstr = "Hello";

        App obj = new App();
        obj.az[0] = App.sz;
        obj.ab[0] = App.sb;
        obj.ac[0] = App.sc;
        obj.as[0] = App.ss;
        obj.ai[0] = App.si;
        obj.al[0] = App.sl;
        obj.af[0] = App.sf;
        obj.ad[0] = App.sd;
        obj.astr[0] = App.sstr;

        System.out.println("Hello, world!");
    }
}
