package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;
import com.project.test.database.Entities.Post;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.project.test.database.Entities.Post_Table;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class PostController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public PostController() {
    }

    public Post addNewPost(int postalCode, String name){
        Post post = new Post(postalCode,name,CurrentDate,CurrentDate);
        post.save();

    return post;
}

    public List<Post> GetAllRecordsFromTable(){

        return SQLite.select().from(Post.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<Post> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }

    public static Post getPostByPostalCode(int postalCode){
        List<Post> posts = SQLite.select().from(Post.class).where(Post_Table.postal_code.is(postalCode)).queryList();


        return posts.get(0);
    }


}
