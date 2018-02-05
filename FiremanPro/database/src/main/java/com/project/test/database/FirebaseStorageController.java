package com.project.test.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.imageSaver.SaveResourceImage;

import java.io.ByteArrayOutputStream;

/**
 * Created by Zoran on 4.2.2018..
 */

public class FirebaseStorageController {
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageRef = storage.getReference();
    private static StorageReference gndPlanImagesRef = storageRef.child("images/ground_plan_images");
    private static StorageReference profilPicRef = storageRef.child("images/profil_pic");

    private static Uri savedUrl;

    public static Uri storeProfilPicFromURI(String uri, final String name) {


        SimpleImageLoadingListener simpleImageLoadingListener = new SimpleImageLoadingListener() {

            /**
             * Nakon uspješnog pruzimanja slike ona se pohranjuje u memoriju uređaja, a naziv se pohranjuje u bazu podataka.
             * @param imageUri
             * @param view
             * @param loadedImage
             */
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                String name = SaveResourceImage.sha256(imageUri);
                returnUrl(storeProfilImages(loadedImage,name));
                System.out.println("URL_SLIKE: spremljan slika___:" + imageUri);
                //  FirebaseStorageController.storeGroundPlanImages(loadedImage,name);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                returnUrl(null);

            }
        };
        //imageloader
        // Create global configuration and initialize ImageLoader with this config
       // ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();

       // ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.loadImage(uri,
                simpleImageLoadingListener

        );

        return savedUrl;
    }



    public static Uri storeProfilImages(Bitmap bitmap, final String name) {

        StorageReference mountainsRef = profilPicRef.child(name + ".jpg");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                returnUrl(null);
                Log.d("FirebaseStorage","storeProfilImageError");
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                returnUrl(downloadUrl);
                Log.d("FirebaseStorage","store image: " + downloadUrl);
            }
        });

        return savedUrl;
    }


    public static Uri storeGroundPlanImagesFromURI(String uri, final String name) {


        SimpleImageLoadingListener simpleImageLoadingListener = new SimpleImageLoadingListener() {

            /**
             * Nakon uspješnog pruzimanja slike ona se pohranjuje u memoriju uređaja, a naziv se pohranjuje u bazu podataka.
             * @param imageUri
             * @param view
             * @param loadedImage
             */
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                String name = SaveResourceImage.sha256(imageUri);
                returnUrl(storeGroundPlanImages(loadedImage,name));
                System.out.println("URL_SLIKE: spremljan slika___:" + imageUri);
                //  FirebaseStorageController.storeGroundPlanImages(loadedImage,name);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                returnUrl(null);

            }
        };
        //imageloader
        // Create global configuration and initialize ImageLoader with this config
        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();

        //ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.loadImage(uri,
                simpleImageLoadingListener

        );

        return savedUrl;
    }



    public static Uri storeGroundPlanImages(Bitmap bitmap, final String name) {

        StorageReference mountainsRef = gndPlanImagesRef.child(name + ".jpg");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                returnUrl(null);
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                returnUrl(downloadUrl);
                Log.d("FirebaseStorage","store image");
            }
        });

        return savedUrl;
    }

    private static void returnUrl(Uri url) {
        savedUrl = url;
    }


}
