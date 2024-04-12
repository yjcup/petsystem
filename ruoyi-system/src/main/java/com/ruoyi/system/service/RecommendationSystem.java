package com.ruoyi.system.service;

import com.ruoyi.system.domain.OrderRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RecommendationSystem {


    @Autowired
    private OrderService orderService;

    // 构造函数

    // 根据用户ID推荐商品ID
    public List<Integer> recommendProducts(int userId) {
        List<OrderRec> userOrders = orderService.getOrdersByUserId(userId);
        Set<Integer> userProducts = new HashSet<>();
        for (OrderRec order : userOrders) {
            userProducts.add(order.getProductId());
        }

        Map<Integer, Double> recommendationScores = new HashMap<>();
        for (Integer productId : userProducts) {
            List<OrderRec> similarOrders = orderService.getOrdersByProductId(productId);
            for (OrderRec similarOrder : similarOrders) {
                if (!userProducts.contains(similarOrder.getProductId())) {
                    // 计算相似度
                    recommendationScores.put(similarOrder.getProductId(),
                            recommendationScores.getOrDefault(similarOrder.getProductId(), 0.0) + 1.0);
                }
            }
        }

        List<Integer> recommendedProducts = new ArrayList<>(recommendationScores.keySet());
        recommendedProducts.sort((p1, p2) -> Double.compare(recommendationScores.get(p2), recommendationScores.get(p1)));

        return recommendedProducts.subList(0, Math.min(5, recommendedProducts.size()));
    }


//
//
//    public static void main(String[] args) throws SQLException {
//        // 连接数据库
//        String url = "jdbc:mysql://localhost:3306/your_database";
//        String username = "your_username";
//        String password = "your_password";
//        Connection conn = DriverManager.getConnection(url, username, password);
//
//        // 创建推荐系统对象
//        RecommendationSystem recommendationSystem = new RecommendationSystem(conn);
//
//        // 示例：为用户1推荐商品
//        int userId = 1;
//        List<Integer> recommendedProducts = recommendationSystem.recommendProducts(userId);
//        System.out.println("Recommended Products for User " + userId + ":");
//        for (Integer productId : recommendedProducts) {
//            System.out.println(productId);
//        }
//
//        // 关闭数据库连接
//        conn.close();
//    }
}
