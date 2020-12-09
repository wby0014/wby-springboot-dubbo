package com.wby.web.base.shiro.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

/**
 * @Description 会话监听器用于监听会话创建、过期及停止事件
 * @Author wby
 * @Date 2020/4/5 12:09
 */
@Slf4j
@Component
public class JwtSessionListener implements SessionListener {
    //TODO:会话创建时触发
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }

    //TODO:会话过期时触发
    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期：" + session.getId());
    }

    //TODO:退出/会话过期时触发
    @Override
    public void onStop(Session session) {
        System.out.println("会话停止：" + session.getId());
    }
}
