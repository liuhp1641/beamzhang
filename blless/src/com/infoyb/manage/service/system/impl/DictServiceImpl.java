package com.cm.manage.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cm.manage.cache.XMemcachedClientWrapper;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.json.JsonBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.system.IDictDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IDictService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.DictDetail;
import com.cm.manage.vo.system.DictType;

@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl implements IDictService {

    @Autowired
    public IDictDao dictDao;
    @Autowired
    private XMemcachedClientWrapper xMemcachedClientWrapper;

    @Override
    public List<EasyuiTreeNode> getDictType(String model) {
        return dictDao.getDictType(model);
    }

    @Override
    public EasyuiDataGridJson getDictDetail(EasyuiDataGrid dg, String model,
                                            Long typeId) {
        return dictDao.getDictDetail(dg, model, typeId);
    }

    /**
     * 类型添加
     *
     * @param dict
     * @return
     */
    public DictType typeAdd(DictType dict) {
        return dictDao.typeAdd(dict);

    }

    /**
     * 字典类型删除
     *
     * @param id
     */
    public void typeDel(String model, Long id) {
        dictDao.typeDel(model, id);
    }

    /**
     * 字典类型信息
     *
     * @param model
     * @param id
     * @return
     */
    public DictType typeInfo(String model, Long id) {
        return dictDao.typeInfo(model, id);
    }


    /**
     * 字典详细添加
     *
     * @param dict
     * @return
     */

    public DictDetail detailAdd(DictDetail dict) {
        return dictDao.detailAdd(dict);
    }

    /**
     * 字典详细删除
     *
     * @param ids
     */
    public void detailDel(String model, String ids) {
        dictDao.detailDel(model, ids);
    }

    @Override
    public void initMemcached(Long typeId, String model) {
        DictType dictType = dictDao.typeInfo(model, typeId);
        List<Map> mapList = dictDao.getDictDetailList(typeId, model);
        List<DictDetail> dictDetailList = new ArrayList<DictDetail>();
        for (Map map : mapList) {
            DictDetail dictDetail = new DictDetail();
            dictDetail.setId(CommonUtil.formatDouble(map.get("ID")).longValue());
            dictDetail.setTypeId(CommonUtil.formatDouble(map.get("TYPE_ID")).longValue());
            dictDetail.setCode((String) map.get("CODE"));
            dictDetail.setName((String) map.get("NAME"));
            dictDetail.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
            dictDetailList.add(dictDetail);
        }
        if (CommonUtil.isNotEmpty(dictDetailList)) {
            xMemcachedClientWrapper.set(dictType.getCode(), 0, JsonBinder.buildNonDefaultBinder().toJson(dictDetailList));
        }
    }
}
