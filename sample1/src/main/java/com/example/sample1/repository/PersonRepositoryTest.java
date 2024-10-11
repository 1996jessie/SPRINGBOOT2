package com.example.sample1.repository;

import com.example.sample1.entity.PersonEntity;
import com.example.sample1.entity.QPersonEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    @DisplayName("1. 레코드 삽입 save")
    public void personSave() {
        String[] id = {"jung","choi","hong"};
        String[] addr = {"제주","부산","경기","경주"};
        String[] name = {"아이유","윤아","보검","현빈"};
        int[] salary = {2000,3500,2700,4200,5300};

        for(int i=0;i<10;i++) {
            PersonEntity person = new PersonEntity();
            person.setId(id[i% id.length]);
            person.setName(name[i% name.length]);
            person.setAddr(addr[i% addr.length]);
            person.setSalary(salary[i% salary.length]);

            PersonEntity pe = personRepository.save(person);
            System.out.println("pe : " + pe);
        }
    }


    @Test
    @DisplayName("2. 전체 조회")
    public void selectAll() {
        List<PersonEntity> personList = personRepository.findAll();
        for(PersonEntity pe : personList) {
            System.out.println("pe : "  + pe);
        }
    }

    @Test
    @DisplayName("3. 특정 아이디로 조회")
    public void findByName() {
        List<PersonEntity> personList = personRepository.findByName("a");
        for(PersonEntity pe : personList) {
            System.out.println("pe : "  + pe);
        }
    }

    @Test
    @DisplayName("4. 급여가 3000보다 큰 레코드 내림차순 조회")
    public void findBySalaryLessThan() {
        List<PersonEntity> personList = personRepository.findBySalaryLessThan(3000);
        for(PersonEntity pe : personList) {
            System.out.println("pe : "  + pe);
        }
    }

    @Test
    @DisplayName("5. 이름으로 조회")
    public void findByName2() {
        List<PersonEntity> personList = personRepository.findByName2("아이유");
        for(PersonEntity pe : personList) {
            System.out.println("pe : "  + pe);
        }
    }

    @Test
    @DisplayName("6. 이름에 '아' 포함된 레코드 수")
    public void countNameWith() {
        int count = personRepository.countNameWith("아");
        System.out.println("count : " + count);
    }

    @Test
    @DisplayName("7. 이름에 '아' 포함된 레코드 조회")
    public void findNameWith() {
        List<PersonEntity> personList = personRepository.findNameWith("아");
        for(PersonEntity pe : personList) {
            System.out.println("pe : "  + pe);
        }
    }

    //QueryDSL
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("8. 이름에 '아' 포함, 급여 3000보다 작은 레코드 id 내림차순 조회")
    public void findByDSL() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QPersonEntity qPerson = QPersonEntity.personEntity;
        JPAQuery<PersonEntity> query = queryFactory
                .selectFrom(qPerson)
                .where(qPerson.name.like("%아%").and(qPerson.salary.lt(3000)))
                .orderBy(qPerson.id.desc());
        List<PersonEntity> personList = query.fetch();
        for(PersonEntity pe : personList) {
            System.out.println("pe : " + pe);
        }
    }



    //1. 레코드 삽입 save
    //2. 전체 조회
    //3. 특정 아이디로 조회
    //4. 급여가 3000보다 큰 레코드 내림차순 조회(query method)
    //5. 이름으로 조회(query annotation)
    //6. 이름에 '아' 포함된 레코드 개수 @Query
    //7. 이름에 '아' 포함된 레코드 조회 @Query
    //8. 이름에 '아' 포함, 급여 3000보다 작은 레코드 id 내림차순 조회 queryDsl
}
