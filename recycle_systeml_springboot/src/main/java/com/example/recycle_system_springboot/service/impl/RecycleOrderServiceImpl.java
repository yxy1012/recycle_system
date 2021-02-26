package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.*;
import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.*;
import com.example.recycle_system_springboot.pojo.vo.*;
import com.example.recycle_system_springboot.service.RecycleOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecycleOrderServiceImpl implements RecycleOrderService {
    @Resource
    RecycleOrdersDao recycleOrdersDao;
    @Resource
    RecycleOrderDetailDao recycleOrderDetailDao;
    @Resource
    CollectorDao collectorDao;
    @Resource
    ItemDao itemDao;
    @Resource
    RecycleOrderDetail recycleOrderDetail;
    @Resource
    EvaluationDao evaluationDao;
    @Resource
    RecycleOrders recycleOrders;
    @Resource
    AddressDao addressDao;
    @Resource
    RecycleSiteDao recycleSiteDao;
    @Override
    //用户查询所有的订单信息(业务逻辑：根据用户提供的相关信息对所有的订单信息进行查询，运用动态sql，动态的根据用户的需求进行查询)
    public PageInfo<OrdersVo> userfindAllOrders(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllByuser(userid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //用户查询进行中订单信息(业务逻辑：根据用户提供的相关信息对所有的相关信息进行查询，运用动态sql，动态的根据用户的需求进行查询，正在进行中的订单是根据捐赠订单表中的finished_time为空的记录)
    @Override
    public PageInfo<OrdersVo> userfindAllDoingOrders(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByuser(userid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //与用户类似
    @Override
    public PageInfo<OrdersVo> collectorfindAllOrders(int collectorid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllBycollector(collectorid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllBycollector(collectorid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> collectorfindAllDoingOrders(int collectorid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingBycollector(collectorid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingBycollector(collectorid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> adminfindAllOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> adminfindAllDoingOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> userselectCountbytime(int id){
        List<EchartsTimeVo> list=recycleOrdersDao.userselectCountbytime(id);
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> userselectCountbydate(int id){
        List<EchartsTimeVo> list=recycleOrdersDao.userselectCountbydate(id);
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> adminselectCountbydate(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectCountbydate();
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> adminselectCountbytime(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectCountbytime();
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> adminselectAmountbydate(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectAmountbydate();
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> collectorselectCountbydate(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectCountbydate(collectorid);
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> collectorselectCountbytime(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectCountbytime(collectorid);
        return list;
    }
    //用于Echart的数据统计
    @Override
    public List<EchartsTimeVo> collectorselectAmountbydate(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectAmountbydate(collectorid);
        return list;
    }
    //用于更新和确认订单时所有的物品展示(业务逻辑：查询所有的item信息，包括单价、图片等,查询订单详情表，将订单详情表中物品的数量进行处理，返回给前端)
    @Override
    public List<ItemVo> getAllItems(int id) {
        List<ItemVo> result=itemDao.getAllItems();
        List<RecycleOrdersDetailVo> r=recycleOrderDetailDao.selectDetailByRecycleOrderId(id);
        for(RecycleOrdersDetailVo recycleOrdersDetailVo:r){
            if(recycleOrdersDetailVo.getQuantity()>0){
                for(ItemVo itemVo :result){
                    List<ItemRecycleVo> i=itemVo.getItemsList();
                    for(ItemRecycleVo itemRecycleVo:i){
                        if(itemRecycleVo.getItemName().equals(recycleOrdersDetailVo.getItemName())){
                            itemRecycleVo.setQuantity(recycleOrdersDetailVo.getQuantity());
                        }
                    }
                }
            }
        }
        return result;
    }
    //下单所需
    @Override
    public List<ItemVo> getAllItemsbyrecycle() {
        List<ItemVo> result=itemDao.getAllItems();
        return result;
    }
    //用于下单(业务逻辑：从前端获取的所有物品的数据及相关的关于订单的信息，先在订单表中生成一条记录，并获取到订单表的主键，判断他下的订单中的物品数量，若是物品
    // 数量大于0，则添加一条记录到订单详情表中，并且记录相应的总价，在所有的物品遍历完成之后，将总价信息更新到订单表的记录中)
    @Override
        public Boolean placeAnOrder(OrderDto orderVo) {
        Double amount=0.0;
        int i=recycleOrdersDao.insert(orderVo);
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    amount+=(double)item.getItemPrice()*item.getQuantity();
                }
            }
        }
        orderVo.setTotalAmount(amount);
        Address address=addressDao.selectByPrimaryKey(orderVo.getAddressId());
        double j=address.getLongitude();
        double w=address.getLatitude();
        List<RecycleSite> recycleSites=recycleSiteDao.selectAll();
        RecycleSite recycleSite=recycleSites.get(0);
        double min=(recycleSite.getLongitude()-j)*(recycleSite.getLongitude()-j)+(recycleSite.getLatitude()-w)*(recycleSite.getLatitude()-w);
        int recyclesiteid=recycleSite.getSiteId();
        for(RecycleSite r:recycleSites){
            if(((r.getLongitude()-j)*(r.getLongitude()-j)+(r.getLatitude()-w)*(r.getLatitude()-w))<min){
                min=(r.getLongitude()-j)*(r.getLongitude()-j)+(r.getLatitude()-w)*(r.getLatitude()-w);
                recyclesiteid=r.getSiteId();
            }
        }
        int collectorid=collectorDao.selectBysiteid(recyclesiteid);
        orderVo.setCollectorPersonId(collectorid);
        recycleOrdersDao.updateByPrimaryKeySelective(orderVo);
        return true;
    }
    //更新订单表中的信息(业务逻辑：更新订单表中的有关信息（包括地址、预约时间等），先删除订单详情表中的相关信息，再遍历一遍物品，判断物品数量大于0的添加到
    // 订单详情表之后，并且计算总价，在最后更新总价的信息)
    @Override
    public Boolean updateAnOrder(OrderDto orderVo) {
        Double amount=0.0;
        int i=recycleOrdersDao.updateByPrimaryKey(orderVo);
        recycleOrderDetailDao.deleteByRecycleOrderId(orderVo.getRecycleOrderId());
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    amount+=(double)item.getItemPrice()*item.getQuantity();
                }
            }
        }
        orderVo.setTotalAmount(amount);
        recycleOrdersDao.updateByPrimaryKeySelective(orderVo);
        return true;
    }
    //根据主键查询到一条订单的记录
    @Override
    public RecycleOrders findAnOrder(int id) {
        RecycleOrders recycleOrders=recycleOrdersDao.selectByPrimaryKey(id);
        return recycleOrders;
    }
    //管理员给订单安排一个配送员，与捐赠中的安排派送员类似
    @Override
    public int placecollector(int recycleorderId, String collectorname) {
        int collectorId=collectorDao.selectBycollectorname(collectorname);
        boolean result =recycleOrdersDao.updateCollector(recycleorderId,collectorId);
        if(result){
            return recycleorderId;
        }else {
            return 0;
        }
    }

    //派送员对订单的确认(业务逻辑：派送员确认订单时会根据确认时的时间更新订单表中的finished_time，同时，派送员在确认时具有更改订单的相关信息的功能，
    // 所以需要更新相关的订单详情表)
    @Override
    public Boolean confirmOrder(OrderDto orderVo) {
        Double amount=0.0;
        int i=recycleOrdersDao.updateByPrimaryKey(orderVo);
        recycleOrderDetailDao.deleteByRecycleOrderId(orderVo.getRecycleOrderId());
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    amount+=(double)item.getItemPrice()*item.getQuantity();
                }
            }
        }
        orderVo.setTotalAmount(amount);
        recycleOrdersDao.updateByPrimaryKeySelective(orderVo);
        return true;
    }
    //用户对一条订单记录进行评价，与捐赠中的评价类似
    @Override
    public Boolean Evaluateorder(EvaluationVo order) {
        evaluationDao.insert(order);
        recycleOrders.setRecycleOrderId(order.getRecycleOrderId());
        recycleOrders.setEvaluationId(order.getEvaluationId());
        recycleOrdersDao.updateByPrimaryKeySelective(recycleOrders);
        return true;
    }
    //用户删除一条正在进行中的订单记录
    @Override
    public Boolean DeleteOrder(int recycleorderId) {
        recycleOrdersDao.deleteByPrimaryKey(recycleorderId);
        return true;
    }
    //获取所有订单的总金额
    @Override
    public Double getSumAmount() {
        Double result=recycleOrdersDao.selectsumamount();
        return result;
    }

    @Override
    public boolean DeleteCollector(int recycleorderId) {
        boolean result=recycleOrdersDao.updateByDeleteCollertor(recycleorderId);
        return result;
    }
}
