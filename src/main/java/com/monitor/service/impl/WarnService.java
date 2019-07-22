package com.monitor.service.impl;

import com.monitor.common.tools.DateTool;
import com.monitor.dao.IWarnInfoDao;
import com.monitor.entity.model.WarnExtendModel;
import com.monitor.entity.model.WarnModel;
import com.monitor.entity.model.WarnStatusModel;
import com.monitor.entity.param.WarnHistoryParam;
import com.monitor.entity.param.WarnUploadParam;
import com.monitor.service.IWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarnService implements IWarnService {

    @Autowired
    IWarnInfoDao warnInfoDao;

    @Override
    public List<WarnExtendModel> getInTimeWarns(){
        WarnStatusModel warnStatusModel = warnInfoDao.getInTimeWarnStatus();
        if (warnStatusModel == null || warnStatusModel.getWarnStatus() == 0){
            return null;
        }
        String[] ids = warnStatusModel.getWarnIds().split(",");
        return warnInfoDao.getInTimeWarns(ids);
    }

    @Override
    @Transactional
    public int insertWarns(List<WarnUploadParam> warnUploadParams) {
        if (warnUploadParams ==null || warnUploadParams.size()==0){
            warnInfoDao.insertWarnStatus(WarnStatusModel.builder().warnStatus(0).collectTime(DateTool.getStringDate()).build());
            return 0;
        }
        //获取上一次的告警信息
        WarnStatusModel warnStatusModel = warnInfoDao.getInTimeWarnStatus();
        if (warnStatusModel == null || warnStatusModel.getWarnStatus() == 0){
            //上一次没有告警信息
            warnInfoDao.insertWarns(warnUploadParams);
            String ids =  warnUploadParams.stream().map(o -> String.valueOf(o.getId())).collect(Collectors.joining(",", "", ""));
            String codes =  warnUploadParams.stream().map(o -> o.getWarnCode()).collect(Collectors.joining(",", "", ""));
            String collectTime = warnUploadParams.get(0).getCollectTime();
            return warnInfoDao.insertWarnStatus(WarnStatusModel.builder().warnStatus(1).warnIds(ids).warnCodes(codes).collectTime(collectTime).build());
        }else{
            //准备告警状态的codes
            String codes = warnUploadParams.stream().map(o -> o.getWarnCode()).collect(Collectors.joining(",", "", ""));
            String collectTime = warnUploadParams.get(0).getCollectTime();

            String[] warnIds = warnStatusModel.getWarnIds().split(",");
            String[] warnCodes = warnStatusModel.getWarnCodes().split(",");
            List<WarnUploadParam> updateWarnList = new ArrayList<>();
            for (int i = 0; i < warnCodes.length; i++) {
                for (int j = 0; j < warnUploadParams.size(); j++) {
                    if (warnCodes[i].equals(warnUploadParams.get(j).getWarnCode())){
                        warnUploadParams.get(j).setId(Integer.parseInt(warnIds[i]));
                        updateWarnList.add(warnUploadParams.get(j));
                    }
                }
            }

            //装备告警状态的ids
            String ids = "";
            int count = 0;
            //如果有持续上报的告警信息
            if (updateWarnList.size()>0){
                ids = updateWarnList.stream().map(o -> String.valueOf(o.getId())).collect(Collectors.joining(",", "", ""));
                //移除上次有上报的警告信息,剩余的就是新的告警信息
                warnUploadParams.removeAll(updateWarnList);
                for (int i = 0; i < updateWarnList.size(); i++) {
                    count += warnInfoDao.updateWarns(updateWarnList.get(i));
                }
            }
            //新上报的告警信息用插入
            if (warnUploadParams.size()>0){
                if (updateWarnList.size()>0) {
                    ids += ",";
                }
                count += warnInfoDao.insertWarns(warnUploadParams);
                ids += warnUploadParams.stream().map(o -> String.valueOf(o.getId())).collect(Collectors.joining(",", "", ""));
            }

            //插入状态信息
            warnInfoDao.insertWarnStatus(WarnStatusModel.builder().warnStatus(1).warnIds(ids).warnCodes(codes).collectTime(collectTime).build());
            return count;
        }
    }

    @Override
    public List<WarnExtendModel> getHistoryWarns(WarnHistoryParam warnHistoryParam) {
        return warnInfoDao.getHistoryWarns(warnHistoryParam);
    }

    /**
     * 将list转成String
     * @param list
     * @return
     */
    private String getListToString(List list) {
        StringBuffer warnStatusIds = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                warnStatusIds.append(list.get(i));
            } else {
                warnStatusIds.append(list.get(i)).append(",");
            }
        }
        return warnStatusIds.toString();
    }


}
