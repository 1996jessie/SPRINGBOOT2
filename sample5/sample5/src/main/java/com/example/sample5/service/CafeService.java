package com.example.sample5.service;

import com.example.sample5.bean.CafeBean;
import com.example.sample5.entity.CafeEntity;
import com.example.sample5.entity.QCafeEntity;
import com.example.sample5.repository.CafeRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {

    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private final CafeRepository cafeRepository;

    public CafeEntity insertCafe(CafeBean cafeBean) {
        CafeEntity cafeEntity = modelMapper.map(cafeBean, CafeEntity.class);
        return cafeEntity;
    }

    public void saveCafe(CafeEntity cafeEntity) {
        cafeRepository.save(cafeEntity);
    }

    public List<CafeEntity> selectAllMovie() {
        List<CafeEntity> cafeList = cafeRepository.findByOrderByNumDesc();

        return cafeList;
    }

    public CafeEntity findCafeById(String id) {
        CafeEntity cafe = cafeRepository.findCafeById(id);
        return cafe ;
    }

    public CafeEntity selectCafeByNum(int num) {
        CafeEntity cafe = cafeRepository.findByNum(num);
        return cafe;
    }

    public void deleteById(int num){
        cafeRepository.deleteById(num);
    }

    public Page<CafeEntity> getCafeEntity(int page, int size, String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("num").descending());
        if(keyword == null){
            return cafeRepository.findAll(pageable);
        }else{
            return findByNameTempOrder2(keyword,pageable);
        }
    }

    @PersistenceContext
    EntityManager em;

    private Page<CafeEntity> findByNameTempOrder2(String keyword, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCafeEntity qCafe = QCafeEntity.cafeEntity;
        JPAQuery<CafeEntity> query = queryFactory
                .selectFrom(qCafe)
                .where(qCafe.name.like("%"+keyword+"%")
                        .or(qCafe.temp.like("%"+keyword+"%")))
                .orderBy(qCafe.num.desc());
        List<CafeEntity> cafeList = query.fetch();

        System.out.println("pageable.getPageNumber() : " + pageable.getPageNumber());
        System.out.println("pageable.getPageSize() : " + pageable.getPageSize());
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
        int start = (int)pageRequest.getOffset();
        int end = Math.min( (start+ pageRequest.getPageSize()), cafeList.size());

        Page<CafeEntity> page = new PageImpl<CafeEntity>(cafeList.subList(start,end), pageRequest, cafeList.size());
        return page;
    } //findByNameTempOrder2

    public long getTotalCount() {

        return cafeRepository.count();
    }
}