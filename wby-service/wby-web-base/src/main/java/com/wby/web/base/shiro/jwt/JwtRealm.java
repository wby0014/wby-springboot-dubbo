package com.wby.web.base.shiro.jwt;

import com.wby.api.base.system.service.ISysUserService;
import com.wby.common.core.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Description Shiro 授权认证
 * @Author wby
 * @Date 2019/10/31 11:50
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @DubboReference
    private ISysUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 权限信息认证(包括角色以及权限)是用户访问controller的时候才进行验证(redis存储的此处权限信息)
     * 触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("===============Shiro权限认证开始============ [ roles、permissions]==========");
        Long userId = null;
        String loginName = null;
        if (null != principalCollection) {
            LoginUserVo loginUserVoVo = (LoginUserVo) principalCollection.getPrimaryPrincipal();
            userId = loginUserVoVo.getId();
            loginName = loginUserVoVo.getLoginName();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //TODO: 设置用户拥有的角色集合
        Set<String> roleSet = userService.selectUserRoles(userId);
        info.setRoles(roleSet);

        //TODO: 设置用户拥有的权限集合
        Set<String> permissionSet = userService.selectUserPermissions(userId);
        info.addStringPermissions(permissionSet);
        log.info("===============Shiro权限认证成功==============");
        return info;
    }

    /**
     * 用户信息认证是在用户进行登录的时候进行验证
     * 验证用户输入的账号和密码是否正确，错误抛出异常
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("===============Shiro登录认证开始============");
        //TODO: 校验token
        String token = (String) authenticationToken.getCredentials();
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("token为空！");
        }
        //TODO:从token中取出用户名
        String username = jwtTokenUtil.getUserNameFromToken(token);
        if (org.apache.commons.lang3.StringUtils.isEmpty(username)) {
            throw new AuthenticationException("token非法无效!");
        }
        //TODO: 判断用户是否存在
        LoginUserVo loginUserVoVo = userService.selectLoginUserVoByLoginName(username);
        if (ObjectUtils.isEmpty(loginUserVoVo)) {
            throw new AuthenticationException("用户不存在!");
        }
        //TODO: 判断用户状态
        if (loginUserVoVo.getStatus() == 1) {
            throw new AuthenticationException("账号已被锁定,请联系管理员!");
        }

        //TODO:校验token是否超时失效
        if (!jwtTokenUtil.validateToken(token, username)) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        log.info("===============Shiro登录认证成功============");
        return new SimpleAuthenticationInfo(loginUserVoVo, token, getName());
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
