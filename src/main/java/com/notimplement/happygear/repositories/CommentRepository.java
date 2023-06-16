package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    Comment findByCommentId(String id);
    @Query("SELECT c FROM Comment c where c.user.username = :username")
    List<Comment> findAllByUserName(String username);
    List<Comment> findByCommentParentId(String commentParentId);
    @Query("DELETE FROM Comment c WHERE c.commentParentId = :id")
    void deleteAllByCommentParentId(String id);
    void deleteByCommentParentId(String id);
    @Query("SELECT c FROM Comment c WHERE c.product.productId = :id")
    List<Comment> findAllByProductId(Integer id);
}
