package com.kwt.uishine.gallery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import com.baoyz.actionsheet.ActionSheet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import cn.finalteam.toolsfinal.DateUtils;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.FileUtils;
import cn.finalteam.toolsfinal.StringUtils;
import cn.finalteam.toolsfinal.Logger;
import com.kwt.uishine.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class PhotoGet extends AppCompatActivity {
    Button mOpenGallery;
    static String mPhotoTargetFolder;
    File toFile;
    private Uri mTakePhotoUri;
    private ImageView imageView;
    static final int TAKE_REQUEST_CODE = 1001;
    static final int CROP_TAKE_CODE = 1002;
    static final int CHOOSE_REQUEST_CODE = 1003;
    static final int CROP_CHOOSE_CODE = 1004;
    private static final String TAG = "PhotoGet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_get);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.photoImage);
        mOpenGallery = (Button) findViewById(R.id.btn_open_gallery);
        mOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheet.createBuilder(PhotoGet.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消(Cancel)")
                        .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)", "裁剪(Crop)", "编辑(Edit)")
                        .setCancelableOnTouchOutside(true).setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        String path = "/sdcard/pk1-2.jpg";
                        switch (index) {
                            case 0:
                                openGalleryAction();
                                break;
                            case 1:
                                takePhotoAction();
                                break;
                            case 2:
                                if (new File(path).exists()) {

                                } else {
                                    Toast.makeText(PhotoGet.this, "图片不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                if (new File(path).exists()) {

                                } else {
                                    Toast.makeText(PhotoGet.this, "图片不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:
                                break;
                        }
                    }

                }).show();
            }
        });

    }

    /**
     * 调用相机
     */
    private void takePhotoAction() {
        if (checkFile()) {
            mTakePhotoUri = Uri.fromFile(toFile);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);
            startActivityForResult(captureIntent,TAKE_REQUEST_CODE);
        } else {
            Logger.e("create file failure");
        }
    }

    /**
     * 打开相册
     */
    private void openGalleryAction(){
        if (checkFile()) {
            mTakePhotoUri = Uri.fromFile(toFile);
            Intent intent = new Intent(Intent.ACTION_PICK);// 打开相册
            intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
            intent.putExtra("output", mTakePhotoUri);
            startActivityForResult(intent, CHOOSE_REQUEST_CODE);

        }
    }

    /**
     * 裁剪图片
     * @param uri
     * @param outputX
     * @param outputY
     */
    private void cropImageUri(Uri uri, int outputX, int outputY,int requestCode){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    /**
     * 结果回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {
            switch (requestCode){
                case TAKE_REQUEST_CODE :
                    cropImageUri(mTakePhotoUri, 780, 780,CROP_TAKE_CODE);
                    break;
                case CROP_TAKE_CODE :
                    if(mTakePhotoUri != null){
                        Bitmap bitmap = decodeUriAsBitmap(mTakePhotoUri);
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
                case CHOOSE_REQUEST_CODE:
                    mTakePhotoUri = getRealPathFromUri(PhotoGet.this, data.getData());
                    cropImageUri(data.getData(), 780, 780,CROP_CHOOSE_CODE);
                    break;
                case CROP_CHOOSE_CODE:
                    if(mTakePhotoUri != null){
                        Bitmap bitmap = decodeUriAsBitmap(mTakePhotoUri);
                        imageView.setImageBitmap(bitmap);
                    }

                    break;
                default:
                    break;
            }
        } else {
            Log.e(TAG, "requestCode = " + requestCode);
            Log.e(TAG, "resultCode = " + resultCode);
            Log.e(TAG, "data = " + data);
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 初始化图片路径
     * @return
     */
    private boolean checkFile(){
        if (!DeviceUtils.existSDCard()) {
            Toast.makeText(PhotoGet.this,"没有SD卡不能拍照呢~",Toast.LENGTH_SHORT);
            return false;
        }

        File takePhotoFolder = null;
        if (StringUtils.isEmpty(mPhotoTargetFolder)) {
            takePhotoFolder = new File(Environment.getExternalStorageDirectory(), "/DCIM/" + "GalleryFinal" + File.separator);
        } else {
            takePhotoFolder = new File(mPhotoTargetFolder);
        }

        toFile = new File(takePhotoFolder, "IMG" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".jpg");
        return FileUtils.makeFolders(toFile);
    }

    /**
     * 由content://media/external/images/media/*格式转化为file:///storage/sdcard0/DCIM/  格式
     * @param context
     * @param uri
     * @return
     */
    public static Uri getRealPathFromUri(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String img_path = cursor.getString(column_index);
            File file = new File(img_path);
            Uri fileUri = Uri.fromFile(file);
            return fileUri;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

}
