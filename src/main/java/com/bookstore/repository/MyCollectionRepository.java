package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.MyCollection;

@Repository
public interface MyCollectionRepository extends JpaRepository<MyCollection, Integer>{

}
