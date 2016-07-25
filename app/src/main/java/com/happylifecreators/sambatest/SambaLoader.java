package com.happylifecreators.sambatest;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFileInputStream;

/**
 * 使い方
 *
 * JsonLoader task = new JsonLoader();
 * task.setOnCallBack(new CallBackJson(){
 *     @Override
 *     public void CallBack(String Result) {
 *         Toast.makeText(TopActivity.this, Result, 800).show();
 *     };
 * });
 * task.execute(RSS_FEED_URL);
 */
public class SambaLoader extends AsyncTask<String, Integer, String> {


    private SambaLoaderCallBack callbacksamba;

    // コンストラクタ
    public SambaLoader() {}

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... params) {
        String _str = getData(params[0], params[1], params[2], params[3]);
        return _str;
    }

    public void setOnCallBack(SambaLoaderCallBack _cbj){
        callbacksamba = _cbj;
    }

    @Override
    protected void onPostExecute(String result) {
        callbacksamba.CallBack(result);
        callbacksamba = null;
    }

    public String getData(String _user, String _pass, String _server, String _path) {

        String user = _user;
        String pass = _pass;
        String server = _server;
        String path = _path;
        String smb = "smb://" + user + ":" + pass + "@" + server + "/" + path;

        String data = "";

        SmbFileInputStream sfis = null;
        try {
            sfis = new SmbFileInputStream(smb);
            BufferedReader br = new BufferedReader(new InputStreamReader(sfis));

            String str = br.readLine();
            while(str != null){
                System.out.println(str);
                data += str + System.getProperty("line.separator");
                str = br.readLine();
            }

        } catch (SmbException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return data;
    }
}

