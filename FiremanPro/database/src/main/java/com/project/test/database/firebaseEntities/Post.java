package com.project.test.database.firebaseEntities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentSnapshot;
import com.project.test.database.Entities.firebase.Firestore;
import com.project.test.database.interfaces.IPost;

import static android.content.ContentValues.TAG;


/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Post extends Firestore {


    private int postal_code;

    private String Name;

    private IPost iPost;


    public Post() {

    }

    public Post(String name) {

        this.Name = name;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static void getPostById(String id, final IPost iPost) {

        post_collection.document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    //Post post = new Post(documentSnapshot.getString("Name"));
                    Post post = documentSnapshot.toObject(Post.class);
                post.setPostal_code(Integer.valueOf(documentSnapshot.getId()));
                iPost.onPostArrived(post);}
                else {
                    iPost.onPostArrived(null);
                }
            }
        });

    }

}
