package com.turing.fakeapk.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;

import com.turing.fakeapk.service.FileMng;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownLoaderTask extends AsyncTask<Void, Integer, Long> {
    private final String TAG = "DownLoaderTask";
    private URL mUrl;
    private File mFile;
    private ProgressDialog mDialog;
    private int mProgress = 0;
    private ProgressReportingOutputStream mOutputStream;
    private Context mContext;
    String zipIn = "";
    String zipOut = "";

    public DownLoaderTask(String url, String out, Context context) {
        super();
        if (context != null) {
            mDialog = new ProgressDialog(context);
            mContext = context;
        } else {
            mDialog = null;
        }

        try {
            mUrl = new URL(url);
            String fileName = new File(mUrl.getFile()).getName();
            mFile = new File(out, fileName);
            zipIn = out + "/" + fileName;
            zipOut = out;
            Log.d(TAG, "out=" + out + ", name=" + fileName + ",mUrl.getFile()=" + mUrl.getFile());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        //super.onPreExecute();
        if (mDialog != null) {
            mDialog.setTitle("正在下载...");
            mDialog.setMessage(mFile.getName());
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.setOnCancelListener(new OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    cancel(true);
                }
            });
            mDialog.show();
        }
    }

    @Override
    protected Long doInBackground(Void... params) {
        // TODO Auto-generated method stub
        return download();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        //super.onProgressUpdate(values);
        if (mDialog == null)
            return;
        if (values.length > 1) {
            int contentLength = values[1];
            if (contentLength == -1) {
                mDialog.setIndeterminate(true);
            } else {
                mDialog.setMax(contentLength);
            }
        } else {
            mDialog.setProgress(values[0].intValue());
        }
    }

    @Override
    protected void onPostExecute(Long result) {
        // TODO Auto-generated method stub
        //super.onPostExecute(result);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (isCancelled())
            return;

        ToastUtils.getInstance().showLong("下载完成,即将解压覆盖！");

        //String in = "/mnt/sdcard/TouchSprite/lua.zip";
        //String out = "/mnt/sdcard/TouchSprite";
        new ZipExtractorTask(zipIn, zipOut, mContext, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private long download() {
        URLConnection connection = null;
        int bytesCopied = 0;
        try {
            connection = mUrl.openConnection();
            int length = connection.getContentLength();
            if (mFile.exists() && length == mFile.length()) {
                Log.d(TAG, "file " + mFile.getName() + " already exits!!");
                FileMng.getInstance().deleteFile(zipIn);
            }
            mOutputStream = new ProgressReportingOutputStream(mFile);
            publishProgress(0, length);
            bytesCopied = copy(connection.getInputStream(), mOutputStream);
            if (bytesCopied != length && length != -1) {
                Log.e(TAG, "Download incomplete bytesCopied=" + bytesCopied + ", length" + length);
            }
            mOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytesCopied;
    }

    private int copy(InputStream input, OutputStream output) {
        byte[] buffer = new byte[1024 * 8];
        BufferedInputStream in = new BufferedInputStream(input, 1024 * 8);
        BufferedOutputStream out = new BufferedOutputStream(output, 1024 * 8);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, 1024 * 8)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return count;
    }

    private final class ProgressReportingOutputStream extends FileOutputStream {

        public ProgressReportingOutputStream(File file)
                throws FileNotFoundException {
            super(file);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void write(byte[] buffer, int byteOffset, int byteCount)
                throws IOException {
            // TODO Auto-generated method stub
            super.write(buffer, byteOffset, byteCount);
            mProgress += byteCount;
            publishProgress(mProgress);
        }

    }
}