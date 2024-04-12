package com.ruoyi.system.service;


import com.ruoyi.system.domain.OrderRec;
import com.ruoyi.system.domain.Product;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private ISysOrderService sysOrderService;

    @Autowired
    private ProductServiceImpl productService;

    public List<OrderRec> getOrdersByUserId(int userId) {
        List<OrderRec> list = new ArrayList<>();
        SysOrder order = new SysOrder();
        order.setUserId((long) userId);
        List<SysOrder> sysOrders = sysOrderService.selectSysOrderList(order);
        for (SysOrder sysOrder : sysOrders) {
            String goodsList = sysOrder.getGoodsList();
            String[] split = goodsList.split("   ");
            for(String s:split){
                String[] split1 = s.split("\\*");
                Product product = new Product();
                product.setName(split1[0]);
                List<Product> products = productService.selectProductList(product);
                if(!products.isEmpty()){
                    Product product1 = products.get(0);
                    OrderRec orderRec = new OrderRec(Math.toIntExact(sysOrder.getUserId()), Math.toIntExact(product1.getId()));
                    list.add(orderRec);
                }
            }
        }
        Set<OrderRec> set = new HashSet<>(list);
        List<OrderRec> list1 = new ArrayList<>(set);
        return list1;
    }

    public List<OrderRec> getOrdersByProductId(int productId) {
        List<OrderRec> list = new ArrayList<>();
        SysOrder order = new SysOrder();
        Product product2 = productService.selectProductById((long) productId);

        order.setGoodsList(product2.getName());
        List<SysOrder> sysOrders = sysOrderService.selectSysOrderList(order);
        for (SysOrder sysOrder : sysOrders) {
            String goodsList = sysOrder.getGoodsList();
            String[] split = goodsList.split("   ");
            for(String s:split){
                String[] split1 = s.split("\\*");
                Product product = new Product();
                product.setName(split1[0]);
                List<Product> products = productService.selectProductList(product);
                if(!products.isEmpty()){
                    Product product1 = products.get(0);
                    OrderRec orderRec = new OrderRec(Math.toIntExact(sysOrder.getUserId()), Math.toIntExact(product1.getId()));
                    list.add(orderRec);
                }
            }
        }
        Set<OrderRec> set = new HashSet<>(list);
        List<OrderRec> list1 = new ArrayList<>(set);
        return list1;
    }




    }
