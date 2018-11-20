package com.demo.thanksgiving4.demo.thanksgiving4.repository;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    // public List<Movie> findByDeliveredOn();
}