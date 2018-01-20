package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;
import com.project.test.database.Entities.Post;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.project.test.database.Entities.Post_Table;

import java.util.List;

/**
 * Kontrolira dodavanje novih zapisa pošte u bazu podataka, te dohvačanje istih.
 *
 * @author Zoran Hrnčić
 * @see Post
 * <p>
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 */

public class PostController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public PostController() {
    }

    /**
     * dodavanje nove pošte
     *
     * @param postalCode
     * @param name
     * @return novokreirani objekt
     */
    public Post addNewPost(int postalCode, String name) {
        Post post = new Post(postalCode, name, CurrentDate, CurrentDate);
        post.save();

        return post;
    }

    /**
     * @return svi zapisi iztablice Post
     */
    public List<Post> GetAllRecordsFromTable() {

        return SQLite.select().from(Post.class).queryList();


    }


    /**
     * brisanje svih zapisa u tablici Post
     *
     * @see Post
     */
    public void DeleteAllRecordsInTable() {

        final List<Post> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


    /**
     * Dohvaćanje objekta pošte prema prosljeđenom  broju pošte/postal_code
     *
     * @param postalCode
     * @return objekt sa traženim broje ako postoji ili NULL ako ne postoji
     */
    public static Post getPostByPostalCode(int postalCode) {

        try {
            List<Post> posts = SQLite.select().from(Post.class).where(Post_Table.postal_code.is(postalCode)).queryList();
            return posts.get(0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }
    }
}
