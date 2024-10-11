package com.example.sample4.service;

import com.example.sample4.bean.MovieBean;
import com.example.sample4.entity.MovieEntity;
import com.example.sample4.entity.QMovieEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sample4.repository.MovieRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private final MovieRepository movieRepository;

    public MovieEntity insertMovie(MovieBean movieBean) {
        MovieEntity movieEntity = modelMapper.map(movieBean, MovieEntity.class);
        return movieEntity;
    }

    public void saveMovie(MovieEntity movieEntity) {
        movieRepository.save(movieEntity);
    }

    public List<MovieEntity> selectAllMovie() {
        List<MovieEntity> movieList = movieRepository.findByOrderByNumDesc();

        return movieList;
    }

    public MovieEntity findMovieById(String id) {
        MovieEntity movie = movieRepository.findMovieById(id);
        return movie ;
    }

    public MovieEntity selectMovieByNum(int num) {
        MovieEntity me = movieRepository.findByNum(num);
        return me;
    }

    public void deleteById(int no){
        movieRepository.deleteById(no);
    }

    public Page<MovieEntity> getMovieEntity(int page, int size, String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("num").descending());
        if(keyword == null){
            return movieRepository.findAll(pageable);
        }else{
            // 이름에 포함 또는 장르에 포함된 레코드를 번호 내림차순 정렬 조회
            //query method
            //return movieRepository.findByNameContainingOrGenreContainingOrderByNumDesc(keyword,keyword,pageable);

            // query annotation
            //return movieRepository.findByNameGenreOrder(keyword,pageable);

            // query dsl
            return findByNameGenreOrder2(keyword,pageable);
        }//else
    }//getMovieEntity

    @PersistenceContext
    EntityManager em;

    private Page<MovieEntity> findByNameGenreOrder2(String keyword, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMovieEntity qMovie = QMovieEntity.movieEntity;
        JPAQuery<MovieEntity> query = queryFactory
                .selectFrom(qMovie)
                .where(qMovie.name.like("%"+keyword+"%")
                        .or(qMovie.genre.like("%"+keyword+"%")))
                .orderBy(qMovie.num.desc());
        List<MovieEntity> movieList = query.fetch();

        System.out.println("pageable.getPageNumber() : " + pageable.getPageNumber());
        System.out.println("pageable.getPageSize() : " + pageable.getPageSize());
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
        int start = (int)pageRequest.getOffset();
        int end = Math.min( (start+ pageRequest.getPageSize()), movieList.size());

        Page<MovieEntity> page = new PageImpl<MovieEntity>(movieList.subList(start,end), pageRequest, movieList.size());
        return page;
    } //findByNameGenreOrder2




    public long getTotalCount() {
        return movieRepository.count();
    }
}