package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int user_id);

    @Transactional
    @Modifying
    @Query(name = Meal.ALL_SORTED)
    List<Meal> getAll(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(name = Meal.GET_BETWEEN)
    List<Meal> getBetweenHalfOpen(@Param("startDateTime") LocalDateTime startDateTime,
                                  @Param("endDateTime")LocalDateTime endDateTime,
                                  @Param("userId")int userId);
}
