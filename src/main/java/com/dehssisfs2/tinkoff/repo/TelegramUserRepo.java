package com.dehssisfs2.tinkoff.repo;

import com.dehssisfs2.tinkoff.model.TelegramUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TelegramUserRepo extends CrudRepository<TelegramUser, Long> {

//    @Query(value = "select g from Model g where (g.LastUpdate = (select max(u.LastUpdate) from Model u where u.code = :code) and g.code = :code)")
//    List<Model> findLastDataByCode(@Param("code") int code);

    @Query(value = "select u from TelegramUser u where u.UserId = :userId")
    TelegramUser findTelegramUserByUserId(@Param("userId") long userId);
}
