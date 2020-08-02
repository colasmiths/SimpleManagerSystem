package com.demo.cgb.pj.sys.service.realm;

import com.demo.cgb.pj.sys.dao.SysMenuDao;
import com.demo.cgb.pj.sys.dao.SysRoleMenuDao;
import com.demo.cgb.pj.sys.dao.SysUserDao;
import com.demo.cgb.pj.sys.dao.SysUserRoleDao;
import com.demo.cgb.pj.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//credentials 凭证
//algorithm   算法
public class ShiroUserRealm extends AuthorizingRealm {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysUserRoleDao sysUserRoleDao;
    @Resource
    private SysMenuDao sysMenuDao;
    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    //设置凭证匹配对象
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //构建凭证匹配对象
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        super.setCredentialsMatcher(hashedCredentialsMatcher);

    }



    //此方法负责认证信息的获取和封装
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.把authenticationtoken强转为usernamepasswordtoken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2.通过usernamepassword来获取username
        String username = token.getUsername();
        //3.在通过username从数据库找到user这个完整的对象
        SysUser user = sysUserDao.findUserByUserName(username);
        //4.对user进行校验
        if(user==null)
            throw new UnknownAccountException("用户不存在");
        if(user.getValid()==0)
            throw new LockedAccountException("用户账户已被锁定");
        //A ByteSource wraps a byte array and provides additional encoding operations. Most users will find the ByteSource.Util inner class sufficient to construct ByteSource instances.
        //这个ByteSource对象需要用一个Util内部类的构造方法，太深奥了，以后再研究
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        //调用如下的构造函数
        //public SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
        /**
         * @Param principal  认证的实体信息，可以是username，也可以是user这个实体对象
         * @Param hashedCredentials  用户密码
         * @Param credentialsSalt 用户的密码盐，用ByteSource对象封装
         * @Param realmName 当前realm对象的name，直接调用getName()方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,getName());

        return info;
    }

    //此方法负责授权信息的获取和封装
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.用于获取用户id
        SysUser user =(SysUser) principalCollection.getPrimaryPrincipal();
        //2.基于用户id查询角色id
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(user.getId());
        if(roleIds==null||roleIds.isEmpty())
            throw new AuthorizationException();
        Integer[] array={};
        //3.基于角色id查询菜单id
        List<Integer> menusIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
        if(menusIds==null||menusIds.isEmpty())
            throw new AuthorizationException();
        //4.基于菜单id获取权限表视
        List<String> permissionList = sysMenuDao.findPermissions(menusIds.toArray(array));
        if(permissionList==null||permissionList.isEmpty())
            throw new AuthorizationException();
        //5.封装数据并返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissionSet = new HashSet<>();
        for (String permission:permissionList) {
            if(!StringUtils.isEmpty(permission)){
                permissionSet.add(permission);
            }
        }
        System.out.println(permissionSet);
        info.setStringPermissions(permissionSet);
        return info;
        

    }
}
