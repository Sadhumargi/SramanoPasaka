package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.squareup.picasso.Picasso;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.ImageCompressionAsyncTask;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.ProfileImage;
import com.sramanopasaka.sipanionline.sadhumargi.utils.FileUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class UploadPhotoFragment extends BaseFragment implements GUICallback{
    private Uri imageCaptureFile;
    private View view = null;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_PICK_GALLERY = 3;
    private static final int REQUEST_CODE_PICKER = 5;
    private static final int CAPTURE_PICTURE_REQUESTCODE = 2222;
    private String tempfileName = "avatar.jpg";
    public static final String IMAGE_DIRECTORY_NAME = "Sadhumargi";
    private ImageView photo = null;
    public static UploadPhotoFragment newInstance() {
        return new UploadPhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upload_photo, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        photo = (ImageView) view.findViewById(R.id.photo);


        if (!TextUtils.isEmpty(PreferenceUtils.getProfileImageUrl(getActivity())) && !TextUtils.isEmpty(PreferenceUtils.getProfileImageId(getActivity()))) {
            Picasso.with(getActivity()).load(PreferenceUtils.getProfileImageUrl(getActivity())+"/"+PreferenceUtils.getProfileImageId(getActivity())).placeholder(R.drawable.profilemg)// Place holder image from drawable folder
                    .error(R.drawable.profilemg).resize(150, 150).centerCrop()
                    .into(photo);
        }

        view.findViewById(R.id.uploadPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String[] items = new String[]{"Take Photo", "Choose From Library"};
                DialogueUtils.showSingleChoiceDialog(getActivity(), R.string.add_photo, items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        int lw = ((android.support.v7.app.AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (lw == 0) {
                            requestCameraPermission();
                        } else if (lw == 1) {
                            BrowsePicture();
                        }  else {
                            dialog.dismiss();
                        }
                    }
                });*/

                ImagePicker.create(getActivity())
//                        .returnAfterFirst(true) // set whether pick or camera action should return immediate result or not. For pick image only work on single mode
                        .folderMode(true) // folder mode (false by default)
//                        .folderTitle(getString(R.string.app_name)) // folder selection title
//                        .imageTitle("Tap to select") // image selection title
                        .single() // single mode
                        //.multi() // multi mode (default mode)
                        //.limit(10) // max images can be selected (99 by default)
                        .showCamera(true) // show camera or not (true by default)
                        .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                        //.origin(images) // original selected images, used in multi mode
                        //.theme(R.style.CustomImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                        .enableLog(false) // disabling log
                        //.imageLoader(new GrayscaleImageLoder()) // custom image loader, must be serializeable
                        .start(REQUEST_CODE_PICKER); // start image picker activity with request code
            }
        });

    }

    public void BrowsePicture() {

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if ( checkPermission() ) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_PICK_GALLERY);
            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_PICK_GALLERY);
            }

        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);//
            startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_PICK_GALLERY);
        }
    }

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if ( currentAPIVersion >= Build.VERSION_CODES.M ) {
            if ( ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                if ( ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) ) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void requestCameraPermission() {
        if ( !canAccessCamera() ) {

            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            }
        } else {
            try {
                File f = new File(
                        Environment.getExternalStorageDirectory(),
                        tempfileName);
                if ( f.exists() && f.canWrite() )
                    f.delete();
                Uri imageUri = Uri.fromFile(f);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                startActivityForResult(intent, CAPTURE_PICTURE_REQUESTCODE);


            } catch (ActivityNotFoundException e) {
            } catch (Exception e) {
            }
        }
    }

    private boolean canAccessCamera() {
        return (hasPermission(android.Manifest.permission.CAMERA));
    }

    private boolean hasPermission(String perm) {
        int permission = ContextCompat.checkSelfPermission(getActivity(), perm);
        Log.e("permission=", "" + permission);
        return (PackageManager.PERMISSION_GRANTED == permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        Log.e("onRequestsResult", "requestCode=" + requestCode);
        if ( requestCode == REQUEST_CAMERA_PERMISSION ) {

            if ( grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED ) {
                Log.e("You need permission", "to access camera");


            } else {
                requestCameraPermission();
            }
        } else if ( requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE ) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);//
            startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_PICK_GALLERY);
        }

    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_CANCELED != resultCode) {
            if (requestCode == REQUEST_LOAD_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null) {

                // When an Image is picked
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String image = cursor.getString(columnIndex);
                cursor.close();

                Uri sourceImage = Uri.parse("file:////" + image);


                galleryAddPic(sourceImage.getPath());

                ImageCompressionAsyncTask imageCompressionAsyncTask = new ImageCompressionAsyncTask(getActivity(), 196, 196);
                imageCompressionAsyncTask.setOnImageCompressed(new ImageCompressionAsyncTask.OnImageCompressed() {
                    @Override
                    public void onCompressedImage(ProfileImage profileImage) {
                        // format to be added.
                       // MyProfileActivity.this.mProfileImage = profileImage.imageString;
                       // MyProfileActivity.this.changeProfile(profileImage.image);
                        Log.e("----",""+profileImage.imageString);
                    }
                });
                imageCompressionAsyncTask.execute(sourceImage.toString());


            } else if (requestCode == REQUEST_LOAD_CAMERA_IMAGE && resultCode == Activity.RESULT_OK) {
                if (imageCaptureFile != null) {
                    galleryAddPic(imageCaptureFile.getPath());
                  //  beginCrop(imageCaptureFile);
                } else {
                    Toast.makeText(getActivity(), "Unable to get image", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == REQUEST_CROP) {
                //handleCrop(resultCode, data);
            }
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_PICK_GALLERY) {
                Uri selectedFileUri = data.getData();
                if (selectedFileUri == null)
                    Toast.makeText(
                            getActivity(),
                            "File type not supported",
                            Toast.LENGTH_LONG).show();
                else {

                    final String selectedFilePath = FileUtils.getPath(
                            getActivity(), selectedFileUri);
                    Log.e("selectedFilePath", selectedFilePath);

                    ImageCompressionAsyncTask imageCompressionAsyncTask = new ImageCompressionAsyncTask(getActivity(), 196, 196);
                    imageCompressionAsyncTask.setOnImageCompressed(new ImageCompressionAsyncTask.OnImageCompressed() {
                        @Override
                        public void onCompressedImage(ProfileImage profileImage) {
                            UploadPhotoFragment.this.changeProfilerPicture(profileImage.image);
                            Log.e("----", "" + profileImage.imageString);
                        }
                    });
                    imageCompressionAsyncTask.execute("file:////" + selectedFilePath);


                }


            } else if (requestCode == CAPTURE_PICTURE_REQUESTCODE) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                if (f != null && f.listFiles() != null && f.listFiles().length > 0) {
                    for (File temp : f.listFiles()) {
                        if (temp.getName().equals(tempfileName)) {
                            f = temp;
                            break;
                        }
                    }
                    final String filePath = f.getAbsolutePath();
                    ImageCompressionAsyncTask imageCompressionAsyncTask = new ImageCompressionAsyncTask(getActivity(), 196, 196);
                    imageCompressionAsyncTask.setOnImageCompressed(new ImageCompressionAsyncTask.OnImageCompressed() {
                        @Override
                        public void onCompressedImage(ProfileImage profileImage) {
                            UploadPhotoFragment.this.changeProfilerPicture(profileImage.image);
                            Log.e("Image---",""+profileImage.imageString);
                        }
                    });
                    imageCompressionAsyncTask.execute("file:////" + filePath);


                }
            }else if (requestCode == REQUEST_CODE_PICKER && resultCode == getActivity().RESULT_OK && data != null) {
                ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);
                if(images!=null && images.size()>0) {
                    ImageCompressionAsyncTask imageCompressionAsyncTask = new ImageCompressionAsyncTask(getActivity(), 196, 196);
                    imageCompressionAsyncTask.setOnImageCompressed(new ImageCompressionAsyncTask.OnImageCompressed() {
                        @Override
                        public void onCompressedImage(ProfileImage profileImage) {
                            UploadPhotoFragment.this.changeProfilerPicture(profileImage.image);
                            Log.e("Image---",""+profileImage.imageString);
                            //File file = new File("file:////" + images.get(0).getPath());
                            /*RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new byte[]{profileImage.image});
                            MultipartBody.Part imageFileBody = MultipartBody.Part.createFormData("userfile", "profileImage", requestBody);
                            RequestProcessor requestProcessor = new RequestProcessor(UploadPhotoFragment.this);
                            requestProcessor.uploadProfilePicture(imageFileBody);*/
                        }
                    });
                    imageCompressionAsyncTask.execute("file:////" + images.get(0).getPath());
                   try {

                   }catch (Exception ex){
                       new CustomToast().showErrorToast(getActivity(),view,"This file is not supported, please select ");
                   }
                    Log.e("----", "" + images.get(0).getPath());
                }
            }

        }
    }

    public void changeProfilerPicture(Bitmap bitmap){
        photo.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {



            }else{
                new CustomToast().showErrorToast(getActivity(),view,"Please check your internet connection");
            }

        }
    }
}
