package com.manroid.test.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.manroid.test.util.Util;

public class ConvertTask extends AsyncTask<Boolean, Void, Void> {

    private ProgressDialog progressDialog;
    private Context context;

    public ConvertTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Boolean... booleans) {
        Util.scanContact(context,booleans[0]);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Đang cập nhật danh bạ .... !!!");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
        Toast.makeText(context, "Đã cập nhật thành công !!!", Toast.LENGTH_SHORT).show();
    }
}