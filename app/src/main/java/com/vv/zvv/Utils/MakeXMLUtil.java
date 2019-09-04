package com.vv.zvv.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeXMLUtil {
    /**
     * 根路径,输来的目标路径
     */
//    public static final String ROOT_PATH = "/values/values-{0}x{1}/"; //生成的文件在 项目所在的根目 名为 values 文件夹
//    public static final String ROOT_PATH = "C://Users/zvv00/Desktop/values/values-{0}x{1}/"; // 桌面 名为 values 的文件夹内
    public static final String ROOT_PATH = "/Users/zhangxiaowu/AndroidStudioProjects/4 XMLFile/values/values-{0}x{1}/"; // mac 名为 values 的文件夹内

    //标准屏幕尺寸
    /**
     * 每个屏幕的宽度分成320份
     */
    public static final int DW = 1080;
    /**
     * 每个屏幕的高度分480份
     */
    public static final int DH = 1920;

    /**
     * 宽度上的xml 模板
     */
    public static final String W_TEMPLATE = "<dimen name=\"xx{0}\">{1}px</dimen>\n";
    public static final String W_TEMPLATE_NEGATIVE = "<dimen name=\"xx0{0}\">{1}px</dimen>\n";

    /**
     * 高度上的xml 模板
     */
    public static final String H_TEMPLATE = "<dimen name=\"yy{0}\">{1}px</dimen>\n";
    public static final String H_TEMPLATE_NEGATIVE = "<dimen name=\"yy0{0}\">{1}px</dimen>\n";

    public static final String SUPPOR_PX = "320,480;" +
            "480,800;480,845;480,854;" +
            "540,960;" +
            "600,800;600,1024;640,960;678,1280;" +
            "720,1080;720,1184;720,1196;720,1208;720,1280;720,1440;750,1334;768,1024;" +
            "800,1280;" +
            "1080,1600;1080,1776;1080,1794;1080,1800;1080,1803;1080,1812;1080,1920;" +
            "1080,2040;1080,2160;1080,2220;" +
            "1080,2242;1080,2244;1080,2246;1080,2280;1080,2316;1080,2340;" +
            "1125,2436;1152,1920;1200,1920;1242,2208;" +
            "1440,2000;1440,2392;1440,2560;1440,2880;1440,2960;" +
            "1536,2048;1800,2560";

    public static void main(String[] args) {

        String[] split = SUPPOR_PX.trim().split(";");
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].trim().split(",");
            makeString(Integer.valueOf(split1[0].trim()), Integer.valueOf(split1[1].trim()));
        }

    }

    /**
     * 屏幕 对应的 px
     *
     * @param w 宽
     * @param h 高
     */
    private static void makeString(int w, int h) {
        float cellw = w * 1f / DW;
        StringBuffer sb1 = new StringBuffer();
        sb1.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?> \n");
        sb1.append("<resources>\n");
        for (int i = -50; i < DW; i++) {
            if (i >= 0) {
                sb1.append(W_TEMPLATE.replace("{0}", Math.abs(i) + "").replace("{1}", change(cellw * i) + ""));
            } else {
                sb1.append(W_TEMPLATE_NEGATIVE.replace("{0}", Math.abs(i) + "").replace("{1}", change(cellw * i) + ""));
            }
        }
        sb1.append(W_TEMPLATE.replace("{0}", DW + "").replace("{1}", w + ""));
        sb1.append("</resources>");


        float cellh = h * 1f / DH;
        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?> \n");
        sb2.append("<resources>\n");
        for (int i = -70; i < DH; i++) {
            if (i >= 0) {
                sb2.append(H_TEMPLATE.replace("{0}", Math.abs(i) + "").replace("{1}", change(cellh * i) + ""));
            } else {
                sb2.append(H_TEMPLATE_NEGATIVE.replace("{0}", Math.abs(i) + "").replace("{1}", change(cellh * i) + ""));
            }
        }
        sb2.append(H_TEMPLATE.replace("{0}", DH + "").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = ROOT_PATH.replace("{0}", "" + h).replace("{1}", "" + w);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            file.delete();
        }


        File layoutxFile = new File(path + "layout_dimen_xx.xml");
        File layoutyFile = new File(path + "layout_dimen_yy.xml");

        writer(sb1, layoutxFile);
        writer(sb2, layoutyFile);
    }

    private static void writer(StringBuffer stringBuffer, File layoutxFile) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(layoutxFile));
            printWriter.print(stringBuffer.toString());
        } catch (Exception e) {

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private static int change(float v) {
        int temp = (int) (v * 100);
        return (int) (temp / 100f);
    }
}
