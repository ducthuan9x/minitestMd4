package service;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IPostService extends IGeneralService<Post> {
    Page<Post> findAllByTitle(String title, LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);


    Page<Post> showListAsc(Pageable pageable);

     Page<Post> findAll(Pageable pageable) ;
    Page<Post> showListOfCreateAtAsc(Pageable pageable);


}
