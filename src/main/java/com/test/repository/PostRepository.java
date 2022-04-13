package com.test.repository;

import com.test.model.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {
    public List<Post> getAll(){
        return listAll();
    }
}
