package com.tiva.ISER;

/**
 * Hello world!
 *
 */
public class App
{
    // field (basic)
    public boolean fz;
    public char fc;
    public byte fb;
    public short fs;
    public int fi;
    public long fl;
    public float ff;
    public double fd;
    public String fstr;
    // field (array)
    public boolean[] az = new boolean[1];
    public char[] ac = new char[1];
    public byte[] ab = new byte[1];
    public short[] as = new short[1];
    public int[] ai = new int[1];
    public long[] al = new long[1];
    public float[] af = new float[1];
    public double[] ad = new double[1];
    public String[] astr = new String[1];

    // static (basic)
    public static boolean sz;
    public static char sc = 'b';
    public static byte sb;
    public static short ss;
    public static int si;
    public static long sl;
    public static float sf;
    public static double sd;
    public static String sstr;
    // static (array)
    public static boolean[] saz = new boolean[1];
    public static char[] sac = new char[1];
    public static byte[] sab = new byte[1];
    public static short[] sas = new short[1];
    public static int[] sai = new int[1];
    public static long[] sal = new long[1];
    public static float[] saf = new float[1];
    public static double[] sad = new double[1];
    public static String[] sastr = new String[1];

    public static void main(String[] args) {
        App obj = new App();
        obj.fz = true;
        obj.fc = 'a';
        obj.fb = 1;
        obj.fs = 2;
        obj.fi = 3;
        obj.fl = 4;
        obj.ff = 5.0f;
        obj.fd = 6.0;
        obj.fstr = "Hello";

        obj.az[0] = obj.fz;
        obj.ac[0] = obj.fc;
        obj.ab[0] = obj.fb;
        obj.as[0] = obj.fs;
        obj.ai[0] = obj.fi;
        obj.al[0] = obj.fl;
        obj.af[0] = obj.ff;
        obj.ad[0] = obj.fd;
        obj.astr[0] = obj.fstr;

        App.sz = obj.az[0];
        App.sc = obj.ac[0];
        App.sb = obj.ab[0];
        App.ss = obj.as[0];
        App.si = obj.ai[0];
        App.sl = obj.al[0];
        App.sf = obj.af[0];
        App.sd = obj.ad[0];
        App.sstr = obj.astr[0];

        App.saz[0] = App.sz;
        App.sac[0] = App.sc;
        App.sab[0] = App.sb;
        App.sas[0] = App.ss;
        App.sai[0] = App.si;
        App.sal[0] = App.sl;
        App.saf[0] = App.sf;
        App.sad[0] = App.sd;
        App.sastr[0] = App.sstr;

        obj.fz = App.saz[0];
        obj.fc = App.sac[0];
        obj.fb = App.sab[0];
        obj.fs = App.sas[0];
        obj.fi = App.sai[0];
        obj.fl = App.sal[0];
        obj.ff = App.saf[0];
        obj.fd = App.sad[0];
        obj.fstr = App.sastr[0];

        // local array
        int[] a = new int [10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        System.out.println("Hello, world!");
    }
}
