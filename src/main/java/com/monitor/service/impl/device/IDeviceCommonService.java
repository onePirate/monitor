package com.monitor.service.impl.device;

import com.monitor.common.constant.CommonConstant;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.exception.CustomerException;
import com.monitor.dao.IDeviceDao;
import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.param.*;
import com.monitor.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDeviceCommonService implements IDeviceService {


    @Autowired
    IDeviceDao deviceDao;

    @Override
    public Object getDeviceInTimeStatus(String tableName) {
        if (CommonConstant.TABLE_AGV.equals(tableName)){
            return deviceDao.getAgvInTimeData(new ModelParamBo(tableName,null));
        }
        return deviceDao.getInTimeData(new ModelParamBo(tableName,null));
    }

    @Override
    public int batchInsert(String tableName,Object uploadParamList) {
        if (CommonConstant.TABLE_AGV.equals(tableName)){
            return deviceDao.betchInsertAgv((List<AgvUploadParam>)uploadParamList);
        }else if(CommonConstant.TABLE_YQJ.equals(tableName)){
            return deviceDao.betchInsertYqj((List<YqjUploadParam>)uploadParamList);
        }else if(CommonConstant.TABLE_RLJ.equals(tableName)){
            return deviceDao.betchInsertRlj((List<RljUploadParam>)uploadParamList);
        }else if(CommonConstant.TABLE_SSJ.equals(tableName)){
            return deviceDao.betchInsertSsj((List<SsjUploadParam>)uploadParamList);
        }else if(CommonConstant.TABLE_MD_ROBOT.equals(tableName)){
            return deviceDao.betchInsertMdRobot((List<MonitorUploadParam>)uploadParamList);
        }else{
            throw new CustomerException(StateEnum.REQ_HAS_ERR);
        }
    }
}
