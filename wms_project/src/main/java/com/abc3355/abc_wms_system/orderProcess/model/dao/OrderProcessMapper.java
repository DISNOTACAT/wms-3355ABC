package com.abc3355.abc_wms_system.orderProcess.model.dao;

import com.abc3355.abc_wms_system.orderProcess.model.dto.OrderDetailResDTO;
import com.abc3355.abc_wms_system.orderProcess.model.dto.OrderListResDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderProcessMapper {
    List<OrderListResDTO> selectAllOrders();
    List<OrderListResDTO> selectOrdersByName(String name);
    List<OrderListResDTO> selectOrdersByStatus(String status);
    List<OrderListResDTO> selectMyOrders(String userId);
    List<OrderListResDTO> selectMyOrdersByStatus(Map<String, Object> map);
    List<OrderDetailResDTO> selectOrderDetails(int orderNo);
    int updateOrderStatus(@Param("orderNo") int orderNo, @Param("orderStatusNo") int orderStatusNo);
    Integer getStockAmount(int productNo);
    int updateHQInventoryAmount(Map<String, Integer> map);
    int updateBranchesInventoryAmount(Map<String, Integer> map);
    int insertBranchesInventoryAmount(Map<String, Integer> map);
    int selectInventoryData(Map<String, Integer> map);


}
