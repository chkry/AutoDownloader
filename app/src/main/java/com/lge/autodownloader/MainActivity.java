package com.lge.autodownloader;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    public static final int progress_bar_type = 0;
    private static final String LOG_TAG = "MAIN_ACTIVITY";
    private ProgressDialog pDialog;
    public static final String INTERNAL_FILES_DIR = "/data/data/com.lge.autodownloader/files/tmp/";
    public static final String TEST_ZIP_NAME = "testcases.zip";
    java.lang.Process proc = null;
    BufferedReader stdInput = null;
    public String downloadvalue = "";
    public String CMD_LOCAL = "ls";
    public ArrayList array = new ArrayList<String>();
    String finalvalue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
//                new BackgroundClass().execute("http://collab.lge.com/main/download/attachments/588261386/Automator-Debug.apk?version=1&modificationDate=1486354681478&api=v2");
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ourclass.in/CHAKRIFILES/Automator-Debug.apk"));
//                startActivity(browserIntent);
//                Intent testIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ourclass.in/CHAKRIFILES/testcases.zip"));
//                startActivity(testIntent);
//                                          try {
//                                              java.lang.Runtime.getRuntime().exec("ln -s /data/local/tmp /data/data/com.lge.autodownloader/files/");
//                                              java.lang.Runtime.getRuntime().exec("chmod 777 /data/data/com.lge.autodownloader/files/tmp");
//
//                                          } catch (IOException e) {
//                                              e.printStackTrace();
//                                          }
//                                          copyResultToSdCard();
//                                          try {
//                                              java.lang.Runtime.getRuntime().exec("cp /data/data/com.lge.autodownloader/files/testcases.zip /data/data/com.lge.autodownloader/files/tmp/");
//
//                                          } catch (IOException e) {
//                                              e.printStackTrace();
//                                          }
//                                          try {
//                                              java.lang.Runtime.getRuntime().exec("cp /data/data/com.lge.autodownloader/files/testcases.zip /data/data/com.lge.autodownloader/files/tmp/");
//
//                                          } catch (IOException e) {
//                                              e.printStackTrace();
//                                          }
//                                      }
//                                  });
                TextView txtview = (TextView)findViewById(R.id.downvalue);
                txtview.setText("Starting");
                EditText edtxt = (EditText)findViewById(R.id.editText);
                CMD_LOCAL = edtxt.getText().toString();

                try {
                    proc = Runtime.getRuntime().exec(CMD_LOCAL);
                    stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }



                try {
                    if (proc != null && stdInput != null) {
                        String str_stdIn = "piyush";
                        while ((str_stdIn = stdInput.readLine()) != null) {
                            System.out.println(str_stdIn);
                            array.add(str_stdIn.toString());

//
//                            if (str_stdIn.contains("testcases.zip")) {
//
//                                Log.e(LOG_TAG, "Traversed into Downloads Folder successfully");
//                                txtview = (TextView) findViewById(R.id.downvalue);
//                                txtview.setText("Found testcases.zip");
//                            }
//                            if (str_stdIn.contains("testcases")) {
//
//                                Log.e(LOG_TAG, "Traversed into Downloads Folder successfully");
//                                txtview = (TextView) findViewById(R.id.downvalue);
//                                txtview.setText("Found testcases file in downloads");
//                            }

                        }

                        Object[] objDays = array.toArray();
                        String[] strDays = Arrays.copyOf(objDays, objDays.length, String[].class);

                        for(int i=0;i<strDays.length;i++){
                            finalvalue += strDays[i];
                        }

                        txtview = (TextView) findViewById(R.id.downvalue);
                        txtview.setText(finalvalue);

                    } else {
                        Log.e(LOG_TAG, "runAutomationTc() something is NULL ...!!!!!!!!!!!!!");
                        if (proc == null) Log.e(LOG_TAG, "runAutomationTc() PROC is NULL ...!!!!!!!!!!!!!");
                        if (stdInput == null) Log.e(LOG_TAG, "runAutomationTc() stdInput is NULL ...!!!!!!!!!!!!!");
                        txtview = (TextView) findViewById(R.id.downvalue);
                        txtview.setText("InElse");
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "runAutomationTc() EXCEPTION2e");
                    Log.i(LOG_TAG, "runAutomationTc() SWAPNIL== uncaught exception -->");
                    e.printStackTrace();
                } finally{
                    try {
                        Log.i(LOG_TAG, "runAutomationTc() trying to close reader.");
                        if (stdInput!= null) stdInput.close();
                        if (proc != null) proc.destroy();
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "EXCEPTIONnnnnn : runAutomationTc() CLOSE()");
                        e.printStackTrace();
                    }
                }





            }
        }); //E.O donwload button click


        Button resetBtn = (Button) findViewById(R.id.resetbtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalvalue = "";
                TextView txtview = (TextView)findViewById(R.id.downvalue);
                txtview.setText(finalvalue);
                array.clear();

            }
        });

    }//E.O.onCreate




    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void copyResultToSdCard() {
        try {
            String zipfile = Environment.getExternalStorageDirectory().getPath() + "/" +"Download"+ "/" + "testcases.zip";
            File source = new File(zipfile);


            File targetpath = new File(INTERNAL_FILES_DIR + TEST_ZIP_NAME.toString());
            File target = new File(String.valueOf(targetpath));

//            File source = new File(AllStaticStrings.INTERNAL_FILES_DIR + GenerateResult.EXCEL_FILE_NAME.toString());
//            final String TARGET_RESULT_XLS_FILE_NAME = GenerateResult.EXCEL_FILE_NAME.toString().replace(".xls", AllStaticStrings.factory_version.replace("SOFTWARE VERSION = ", "_")) + ".xls";
//            String targetPath = Environment.getExternalStorageDirectory().getPath() + "/" + AllStaticStrings.EXTERNAL_FOLDER + "/" + TARGET_RESULT_XLS_FILE_NAME;
//            File target = new File(targetPath);
            copyFiles(source, target);
            Log.i(LOG_TAG, "copyResultToSdCard() targetPath=" + targetpath);
        } catch (Exception e) {
            Log.e(LOG_TAG, "copyResultToSdCard() EXCEPTION");
            e.printStackTrace();
        }
    } //e.o.copyResultToSdCard
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void copyFiles(File source, File target) {
        if(source != null && target!= null) {
            if(source.exists()/* && target.exists()*/) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new FileInputStream(source);
                    out = new FileOutputStream(target);
                    // Copy the bits from instream to outstream
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    // in.close();
                    // out.close();
                } catch (Exception IOe) {
                    Log.e(LOG_TAG, "copyFiles() EXCEPTION::");
                    IOe.printStackTrace();
                } finally {
                    try {
                        if (in !=null) {
                            in.close();
                            in = null;
                        }
                        if (out !=null) {
                            out.flush();
                            out.close();
                            out = null;
                        }

                    } catch (Exception e) {
                        Log.i(LOG_TAG, "copyFiles() EXCEPTION= in closing streams.");
                        e.printStackTrace();
                    }
                }
            } else {
                if (!source.exists()) Log.i(LOG_TAG, " copyFiles()>>> !source.exists()");
                if (!target.exists()) Log.i(LOG_TAG, " copyFiles()<<< !target.exists()");
            }
        } else {
            Log.i(LOG_TAG, "source==null OR target==null");
        }
    } //e.o.copyFiles
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//    public void runthisfunction(){
//        try {
//            URL u = new URL("http://collab.lge.com/main/download/attachments/588261386/Automator-Debug.apk?version=1&modificationDate=1486354681478&api=v2");
//            InputStream is = u.openStream();
//
//            DataInputStream dis = new DataInputStream(is);
//
//            byte[] buffer = new byte[1024];
//            int length;
//
//            FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "data/test.apk"));
//            while ((length = dis.read(buffer))>0) {
//                fos.write(buffer, 0, length);
//            }
//
//        } catch (MalformedURLException mue) {
//            Log.e("SYNC getUpdate", "malformed url error", mue);
//        } catch (IOException ioe) {
//            Log.e("SYNC getUpdate", "io error", ioe);
//        } catch (SecurityException se) {
//            Log.e("SYNC getUpdate", "security error", se);
//        }
//    }
//
//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case progress_bar_type: // we set this to 0
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Downloading file. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setMax(100);
//                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pDialog.setCancelable(true);
//                pDialog.show();
//                return pDialog;
//            default:
//                return null;
//        }
//    }


}// END
