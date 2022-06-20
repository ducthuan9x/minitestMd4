package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.PostRepository;
import service.IPostService;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class PostService implements IPostService {
    @Autowired
        PostRepository postRepository;
//    public Iterable<Post> findAll() {
//        return postRepository.findAll();
//    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
         postRepository.save(post);

    }


    public Page<Post> findAllByTitle(String title, LocalDateTime dateFrom, LocalDateTime dateTo,Pageable pageable) {
        return postRepository.findByTitleAndCreateAt(title,dateFrom,dateTo,pageable);
    }

    public Page<Post>showListAsc(Pageable pageable){
        return postRepository.findAllByLikeAsc(pageable);
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll( pageable);
    }

    public  Iterable<Post> top4(){
        return postRepository.findTop4New();
    }
    public Page<Post>showListOfCreateAtAsc(Pageable pageable){
        return postRepository.findAllByCreateAtAsc(pageable);
    }

}
