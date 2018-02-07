package com.project.test.database.RxJava;

import android.os.SystemClock;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.project.test.database.Entities.firebase.Firestore;
import com.project.test.database.firebaseEntities.Post;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Zoran on 7.2.2018..
 */

public class RxJavaTest extends Firestore {


    public static Observable<Post> serverDownload_Observable() {

        return Observable.create(emitter -> {

            post_collection.document("42208")
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        //Post post = new Post(documentSnapshot.getString("Name"));
                        Post post = documentSnapshot.toObject(Post.class);
                        post.setPostal_code(Integer.valueOf(documentSnapshot.getId()));
                        emitter.onNext(post);

                        emitter.onComplete();
                    }
                    else {emitter.onError(new NullPointerException());

                    }
                }
            });

        });
    }

    public static Maybe<Post> serverDownload_maybe(){
        return Maybe.create(emitter -> {

            post_collection.document("42208")
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        //Post post = new Post(documentSnapshot.getString("Name"));
                        Post post = documentSnapshot.toObject(Post.class);
                        post.setPostal_code(Integer.valueOf(documentSnapshot.getId()));
                        emitter.onSuccess(post);

                        emitter.onComplete();
                    }
                    else {
                        emitter.onError(new NullPointerException());
                        emitter.onComplete();

                    }
                }
            });

        });
    }

    public static Single<Post> serverDownlaod_single(){
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    post_collection.document("42208")
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                //Post post = new Post(documentSnapshot.getString("Name"));
                                Post post = documentSnapshot.toObject(Post.class);
                                post.setPostal_code(Integer.valueOf(documentSnapshot.getId()));
                                emitter.onSuccess(post);
                            }
                            else {
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