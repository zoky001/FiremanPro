package com.project.test.database.firebaseEntities;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentSnapshot;
import com.project.test.database.Entities.firebase.Firestore;

import io.reactivex.Single;


/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Post extends Firestore {


    private int postal_code;

    private String Name;

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

    /*public static void getPostById(String id) {

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

    }*/
    public static Single<Post> getPostById(String id) {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    post_collection.document("42208")
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        //Post post = new Post(documentSnapshot.getString("Name"));
                                        Post post = documentSnapshot.toObject(Post.class);
                                        post.setPostal_code(Integer.valueOf(documentSnapshot.getId()));
                                        emitter.onSuccess(post);
                                    } else {
                                        emitter.onError(new NullPointerException());
                                    }
                                }

                            });

                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }

}
