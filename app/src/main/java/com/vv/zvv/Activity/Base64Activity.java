package com.vv.zvv.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.vv.zvv.R;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Base64Activity extends AppCompatActivity {


    String fileUrl = "http://192.168.0.4:8081/BackDoorController/downpdf";

    private ImageView iv_imageView_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);

        initView();
        initData();

    }


    private void initView() {
        iv_imageView_01 = (ImageView) findViewById(R.id.iv_imageView_01);
    }


    private void initData() {
        downloadFile(fileUrl);
    }


    private void downloadFile(final String url) {
//        progressDialog = new ProgressDialog(this);
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {
            }

            @Override
            public void onStarted() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setMessage("亲，努力下载中。。。");
//                progressDialog.show();
//                progressDialog.setMax((int) total);
//                progressDialog.setProgress((int) current);
            }

            @Override
            public void onSuccess(File resultFile) {
                Toast.makeText(Base64Activity.this, "下载成功", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();


                showPdfFile(resultFile);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(Base64Activity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }


    /**
     * File file = new File("E://test.pdf");
     * File dstFile = new File("E://dst_test.png");
     * PDDocument pdDocument;
     * try {
     * pdDocument = PDDocument.load(file);
     * PDFRenderer renderer = new PDFRenderer(pdDocument);
     * // 0表示第一页，300表示转换dpi，越大转换后越清晰，相对转换速度越慢
     * BufferedImage image = renderer.renderImageWithDPI(0,300);
     * ImageIO.write(image, "png", dstFile);
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     */


    private void showPdfFile(File file) {
//        File file = new File("E://test.pdf");
//        File dstFile = new File("E://dst_test.png");
        PDDocument pdDocument;
        try {
            pdDocument = PDDocument.load(file);
            int pageCount = pdDocument.getNumberOfPages();

            Log.e("ds", "showPdfFile: ======================\n =======" + pageCount);

            PDFRenderer renderer = new PDFRenderer(pdDocument);
            // 0表示第一页，300表示转换dpi，越大转换后越清晰，相对转换速度越慢
            for (int i = 0; i < pageCount; i++) {
//                BufferedImage img = renderer.renderImage(i, 1.5f);
//                final java.awt.image.BufferedImage renderImage = renderer.renderImage(i, 1.5f);


////                ImageIO.write(img, "PNG", new File("F:\\pdf\\" + i + ".png"));


//                PdfRenderer.Page  mCurrentPage = renderer.openPage(i);
//                renderer.renderImage(i,300)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 路径 得到 file 得到 bitmap
     *
     * @param
     * @return
     * @throws IOException
     */
    public static Bitmap decodeFile(File f) throws IOException {
        Bitmap b = null;
        int IMAGE_MAX_SIZE = 600;

//        File f = new File(filePath);
//        if (f == null){
//            return null;
//        }
        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = new FileInputStream(f);
        BitmapFactory.decodeStream(fis, null, o);
        fis.close();

        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        fis = new FileInputStream(f);
        b = BitmapFactory.decodeStream(fis, null, o2);
        fis.close();
        return b;
    }


}