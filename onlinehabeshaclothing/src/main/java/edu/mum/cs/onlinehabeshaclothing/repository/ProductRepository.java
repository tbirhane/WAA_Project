package edu.mum.cs.onlinehabeshaclothing.repository;


import edu.mum.cs.onlinehabeshaclothing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//
//    @Query("select photo from Product where id=?1")
//    byte[] getPhotoById(Long id);

}
