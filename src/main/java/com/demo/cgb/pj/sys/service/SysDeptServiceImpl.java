package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.exceptions.ServiceException;
import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.dao.SysDeptDao;
import com.demo.cgb.pj.sys.entity.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptDao sysDeptDao;

    @Override
    public int saveObject(SysDept entity) {
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门不能为空");
        int rows = sysDeptDao.insertObject(entity);

        return rows;
    }

    @Override
    public int updateObject(SysDept entity) {
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门不能为空");
        int rows;

        try{
            rows = sysDeptDao.updateObject(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新失败");
        }
        return rows;



    }

    @Transactional(readOnly = true)
    @Override
    public List<Node> findZTreeNodes() {
        return sysDeptDao.findZTreeNodes();
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        return sysDeptDao.findObjects();
    }

    @Override
    public int deleteObject(Integer id) {
        //1.id的合法性认证
        if(id==null||id<=0)
            throw new ServiceException("数据不合法，id="+id);
        //2.判断此部门是否有下级部门，如有则不可删除
        int childCount = sysDeptDao.getChildCount(id);
        if(childCount>0)
            throw new ServiceException("此部门具有子部门，不允许删除");
        //3.执行删除操作
        int rows = sysDeptDao.deleteObject(id);
        if(rows==0)
            throw new ServiceException("此信息可能已经不存在");
        return rows;

    }
}
