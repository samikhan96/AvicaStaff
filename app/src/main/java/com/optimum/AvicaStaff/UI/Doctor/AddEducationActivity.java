package com.optimum.AvicaStaff.UI.Doctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.optimum.AvicaStaff.Adapters.AdapterEducation;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.HttpUtils.ConfigConstants;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Education;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.AddEducationDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddEducationActivity extends AppCompatActivity {

    EditText et_title, et_desc;
    Button loginBtn;
    ImageView img;
    String s_title, s_desc;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    String imageUrl="";
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);
        user= UserPrefs.getGetUser();
        loginBtn = findViewById(R.id.sendBtn);
        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        img = findViewById(R.id.img);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImage();

        }
    }

    public File getFileFromUri(Context context, Uri uri) {
        File file = null;
        try {
            // Get original file extension
            String extension = ".jpg"; // Default
            String mimeType = context.getContentResolver().getType(uri);
            if (mimeType != null) {
                extension = "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);
            }

            String fileName = System.currentTimeMillis() + extension;
            File dir = context.getCacheDir();
            file = new File(dir, fileName);

            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void uploadImage() {
        File imageFile = getFileFromUri(this, imageUri);

        if (imageFile != null) {
            AppServices.UploadFileRequest("UPLOAD_TAG", ConfigConstants.uploader, imageFile, new ServiceListener<NetworkResponse, VolleyError>() {
                @Override
                public void success(NetworkResponse result) {
                    try {
                        String responseStr = new String(result.data);
                        JSONObject responseJson = new JSONObject(responseStr);

                        if (responseJson.has("data")) {
                            JSONObject dataObject = responseJson.getJSONObject("data");
                            imageUrl = dataObject.getString("url");
                            AppUtils.Toast("Upload Success");
                        } else {
                            AppUtils.Toast("Upload succeeded, but no image URL found.");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        AppUtils.Toast("Upload parsing error");
                    }
                }

                @Override
                public void error(VolleyError error) {
                    AppUtils.Toast("Upload Failed: " + error.getMessage());
                }
            });
        } else {
            AppUtils.Toast("Invalid image file");
        }
    }




    public boolean validate() {
        s_title = et_title.getText().toString();
        s_desc = et_desc.getText().toString();
        if (TextUtils.isEmpty(s_title) || TextUtils.isEmpty(s_desc) || TextUtils.isEmpty(imageUrl)) {
            AppUtils.Toast("Please fill all fields");
            return false;
        }
        Createeducation();
        return true;
    }

    public void Createeducation() {
        AppUtils.showProgressDialog(AddEducationActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uri", imageUrl);
            jsonObject.put("title", s_title);
            jsonObject.put("content", s_desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppServices.Createeducation(AddEducationActivity.class.getSimpleName(), jsonObject, new ServiceListener<String, String>() {
            @Override
            public void success(String success) {
                AppUtils.dismisProgressDialog(AddEducationActivity.this);
                AddEducationDialog cdd = new AddEducationDialog(AddEducationActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(AddEducationActivity.this);

            }
        });
    }
}
