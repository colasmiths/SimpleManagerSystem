package com.demo.cgb.pj.sys.service;


import com.demo.cgb.pj.common.exceptions.ServiceException;
import com.demo.cgb.pj.common.util.PageUtil;
import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.dao.SysLogDao;
import com.demo.cgb.pj.sys.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1){
            throw new IllegalArgumentException("页码不正确");
        }
        int rowCount = sysLogDao.getRowCount(username);
        if(rowCount==0) {
            throw new ServiceException("记录不存在");
        }

        int pageSize = PageUtil.getPageSize();

        int startIndex = PageUtil.getStartIndex(pageCurrent);

        List<SysLog> reconds = sysLogDao.findPageObjects(username,startIndex,pageSize);

        //此方法暂未知用途
        log.error("the re is {}",reconds.toString());

        return PageUtil.newPageObject(pageCurrent,rowCount,pageSize,reconds);

    }

    @Override
    public int deleteObjects(Integer[] ids) {

        int rows = sysLogDao.deleteObjects(ids);
        if(rows>0){
            log.info("删除成功，rows="+rows);
        }
        return rows;
    }
}
