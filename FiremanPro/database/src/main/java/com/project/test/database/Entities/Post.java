package com.project.test.database.Entities;

import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Post extends BaseModel {

    @PrimaryKey(autoincrement = false)
    @Column
    int postal_code;

    @Column
    String name;

    @Column
    Date created_at;

    @Column
    Date updated_at;


    public Post() {
    }

    public Post(int postal_code, String name, Date updated_at, Date created_at) {
        this.postal_code = postal_code;

        this.name = name;

        this.updated_at = updated_at;
        this.created_at = created_at;
    }


    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public static Post getPostById(int id) {

        Post post = SQLite.select().from(Post.class).where(Post_Table.postal_code.is(id)).querySingle();

        if (post != null)
            return post;
        else
            return null;
    }


}
