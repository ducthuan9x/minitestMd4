package repository;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByTitleContaining(String title, Pageable pageable);


    @Query(value = "select * from post order by likes asc", nativeQuery = true)
    Page<Post> findAllByLikeAsc(Pageable pageable);

    @Query(value = "select * from post order by createAt desc limit 4", nativeQuery = true)
    Iterable<Post> findTop4New();

    @Query(value = "select * from post where title like :title and createAt between :dateFrom and :dateTo", nativeQuery = true)
    Page<Post> findByTitleAndCreateAt(@Param("title") String title, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo")LocalDateTime dateTo, Pageable pageable);



    @Query(value = "select * from post order by createAt desc ", nativeQuery = true)
    Page<Post> findAllByCreateAtAsc(Pageable pageable);


}
