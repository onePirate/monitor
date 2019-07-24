package com.monitor.dao;

import com.monitor.entity.bo.CleanParamBo;
import com.monitor.entity.model.WarnExtendModel;
import com.monitor.entity.model.WarnModel;
import com.monitor.entity.model.WarnStatusModel;
import com.monitor.entity.param.WarnHistoryParam;
import com.monitor.entity.param.WarnUploadParam;

import java.util.List;

public interface IWarnInfoDao {

    /**
     * 获取最新的告警状态信息
     * @return
     */
    WarnStatusModel getInTimeWarnStatus();

    /**
     * 获取实时告警信息
     * @return
     */
    List<WarnExtendModel> getInTimeWarns(String[] ids);
    /**
     * 获取历史告警信息
     * @return
     */
    List<WarnExtendModel> getHistoryWarns(WarnHistoryParam warnHistoryParam);

    /**
     * 插入告警状态信息
     * @param warnStatusModel
     * @return
     */
    int insertWarnStatus(WarnStatusModel warnStatusModel);


    /**
     * 插入告警信息
     * @param list
     * @return
     */
    int insertWarns(List<WarnUploadParam> list);

    /**
     * 插入告警信息
     * @param warnUploadParam
     * @return
     */
    int updateWarns(WarnUploadParam warnUploadParam);

    /**
     * 清除警告状态数据
     *
     * @param cleanParamBo
     * @return
     */
    int cleanWarnsStatus(CleanParamBo cleanParamBo);

    /**
     * 清除警告数据
     * @param cleanParamBo
     * @return
     */
    int cleanWarns(CleanParamBo cleanParamBo);

}
