package com.vv.zvv.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * description: Base64字符串和Bitmap相互转化
 * author: zvv
 * date: 2018/1/22 15:38
 * update: 2018/1/22
 * version: 0.0
 */
public class Base64Util {
    public static String baseDemo = "iVBORw0KGgoAAAANSUhEUgAAAX4AAAF+CAMAAACyBIHOAAADAFBMVEX/////AAAAAP8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABLakbNAAAAAXRSTlMAQObYZgAABVVJREFUeNrt3euSmzgQBlA3y/u/cidT2ZrEjmNLQoCEz/mxlWzNGPypEZK45HYDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA+FuEDE60iED84kf84kf84ucI69d/Ug5nTLlUv85H/Ihf/Ihf/Ihf/Ij/5axx9m3vEv8hVzDj1Aulnba9DL13g2zmafHHwPEfkEye2gCdNr1MX5onNEB0OwMs8yaTZ3dBHba8TFyaedIgKPoNgfYeeObxiRj3f4efR7VtnFP8W7+gWa9Z7+hd2379nOo/tS2W5g3EZ5Z/dE1hLdhePv/fkcd82ezxMYPeS7NsOML2n+5Ecfnnm4+J7nuUPcq/qO9/knMc1wFFlw+I7vvSI/+lLYJ+qx77nwNil13NLvmXjnzuD4AY5wScxZUTPYs/+xyeEWW1UxVA9BqYxMFN1drxZ+unbR7377muMPxKTm7d0bWtdLrV97Fj9uxb/D//ENs+eT33cD5gI9Er/6cd/8b815GLtfOHdK/97UfW56z5dDtJ5fO/xcXi71r8udsgbFP+F6/+TSPDwq5nS/7LJYs/eo8QXnb8G/Jfrpn+3UJD7pv+lnsurtj5RM8BT0H6G+65WMYv/mz61e82OCD99vyXifqUui3EYbX/kH+MG3+U5v/dX9d23H8uxnYZ6kfZYfjnTS/lDfBfNJVrtJV47F7/0ftIK0z/fnNFDRAliw4ZvbOpn5637kJ07Hnef1TeN33Rto/sfJovkWWPY6Hp9+tWdvP5776wHp9+Q1X+X1hHr25HVQF83fvxeE0wS9o3S/Yhe5ZVQ/9z4i09WfzTf3fVua3v3+mb5d7tdXDp/z6vPbkPK/fqfDJeTj0mflFQ5Xf4Pq9lPr0rJ/fp+zNuV3wdU2Xp353X8lnX/I/Oc3Pnc8k3YdUevg8XNJ8cADtV/8XDbxlifOX/8Is5yKLDSXlGS/pfSxZNyxaP/XHuduqdpZrLR1qP4+xsXLrLoitt64f0JZFN4f/Kv+38lgUL3utnpF+W//N6bR9cvD93LB+SfslEPd521Q0N8PrD1k9J/339d7o2VjUuXz4m/bf1n7s/hzxN9e9zTeZN/Z8wgxy0+nOmVr1e9Zeu8M++uDfsqTdjxkRr5wjjnnrHSL96vSIuEv8wBR016VfmL/5+DdDwBGv7Ox00QPv8etZZ72kvZ6g7BWT/+Ac5DKYYEBXv5DJZ8c/RG+5Q/cMsRUSe0TdlRfdQvhNbH6se6tCOMd7cU7EHy3TF/6Lx89coZaLB2jJh+m/yn+nfgJ502vUm/2kaYJk+8Yf852qAbfHnOelnljxlFRePP89Kv7AmJjgAlinCrzvkGp8ynCr+Y8Ove8w3p2mApS3uHDn9W+tjnsPGn6fOJaN+Pzq9aXCI+M8Nv2kx5T7/mDj+k8NvS3+kNaot8ec46+u54cdj2uofZbabm5orxH9s+jPkv0ySflsfOHz+yyTp365puXb6Kf5Ta3/w/Cd4ifC2BHu9jyg+Lf6n6d/PwaZ/ecQ6be3HweX/UfH/K/ys7wb65F9zn8/08b8q/bzQfb/LwOlnlpZi0SNzKf6q9Iu7gtzccYj/If0sfwi0+gLYvnL26r9VXU/Pok/bOHvI/j+5+6m38SuXPdRY9aaXPOZ7VG9mHS78W/M1xbO/RsO+rOOFP9Q2dq6EVfiXm/VedXX+U6Zd4kf84kf84kf84kf84kf84kf84qdFeFWe6hc/4hc/4hc/4gcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADO8gPYzDGO7uj7kQAAAABJRU5ErkJggg==";
    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
