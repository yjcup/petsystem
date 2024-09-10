package com.ruoyi.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.system.service.impl.PetResServiceImpl;
import com.ruoyi.system.service.impl.ProductServiceImpl;
import com.ruoyi.system.service.impl.SysOrderServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class PageController{


    @Autowired
    private RecommendationSystem recommendationSystem;


    @GetMapping("/index")
    public String getIndex(ModelMap modelMap){

        // 封面随机三个商品
        List<Product> products = productService.selectProductList(new Product());
        if (products.size() > 3) {
            Collections.shuffle(products);
            products = products.subList(0, 3);
        }
        modelMap.put("products",products);
        // 判断是否登陆
        List<Integer> integers = new ArrayList<>();
        if(ShiroUtils.getSysUser()!=null){
            integers = recommendationSystem.recommendProducts(Math.toIntExact(ShiroUtils.getUserId()));
        }
        List<Product> recpro = new ArrayList<>();
        for(Integer i:integers){
            Product product = productService.selectProductById(Long.valueOf(i));
            recpro.add(product);
        }
        if (recpro.isEmpty()){
            modelMap.put("recpros",products);
        }else{
            modelMap.put("recpros",recpro);
        }
//        modelMap.put("recpros",recpro);
        return "pet/index";
    }

    @GetMapping("/getpetname/{id}")
    @ResponseBody
    public String getpetname(@PathVariable Long id){
        return petService.selectPetById(id).getTitle();
    }



    @GetMapping("/deletecart/{id}")
    @ResponseBody
    public AjaxResult deletecart(@PathVariable Long id){
        Cart cart = new Cart();
        cart.setProductId(id);
        cart.setUserId(ShiroUtils.getUserId());
        List<Cart> carts = cartService.selectCartList(cart);
        cartService.deleteCartById(carts.get(0).getId());
        return AjaxResult.success();
    }



    @Autowired
    private IProductService iProductService;
    @GetMapping("/shop")
    public String shop(@RequestParam(required = false, defaultValue = "1") Integer page, ModelMap model){
        PageHelper.startPage(page,8);
        List<Product> products = iProductService.selectProductList(new Product());
        PageInfo<Product> productPageInfo = new PageInfo<>(products);
        model.put("info",productPageInfo);
        model.put("currentPage",page);

        return "pet/shop_list";
    }

    @GetMapping("/asdf")
    public String asdf(ModelMap map){
        // 1.通过mybaits去数据库里查数据
        Pet pet = petService.selectPetById(1L);
        map.put("pet",pet);

        return "pet/res";
    }





    @GetMapping("/reservation")
    public String Reservation(){
        return "pet/res";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session,ModelMap modelMap){
        // checkout 商品名➕数量
        List<CartItem> cartitems = (List<CartItem>) session.getAttribute("cartitems");
        session.removeAttribute("cartItems");
        List<Product> products = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        if(cartitems==null){
            cartitems = new ArrayList<>();
        }
        for(CartItem cartItem:cartitems){
            Product product = productService.selectProductById(cartItem.getId());
            BigDecimal qu = new BigDecimal(cartItem.getQuantity());
            BigDecimal multiply = product.getPrice().multiply(qu);
            product.setPrice(multiply);
            total = total.add(multiply);
//            product.setCount(Long.valueOf(cartItem.getQuantity()));
            product.setInventory(Long.valueOf(cartItem.getQuantity()));
            products.add(product);
        }
        modelMap.put("total",total);
        modelMap.put("products",products);

        return "pet/checkout";
    }

    @PostMapping("/checkoutpost")
    @ResponseBody
    public AjaxResult getAboutus(@RequestBody List<CartItem> cartItems, HttpSession httpSession) {
        httpSession.setAttribute("cartitems",cartItems);
        return AjaxResult.success();
    }


    @Autowired
    private SysOrderServiceImpl orderService;


    @PostMapping("/orderpost")
    @ResponseBody
    public AjaxResult OrderPost(@RequestBody CheckoutRequest checkoutRequest){
        SysOrder order = new SysOrder();
        order.setAddress(checkoutRequest.getAddress());
        order.setContact(checkoutRequest.getName());
        order.setPhone(checkoutRequest.getPhone());
        StringBuffer sb= new StringBuffer();
        Long userId = ShiroUtils.getUserId();
        for(Product pr:checkoutRequest.getProducts()){
            //修改购物车
            System.out.println(pr);
            sb.append(pr.getName()).append("   ");
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(pr.getId());
            List<Cart> carts = cartService.selectCartList(cart);
            if(!carts.isEmpty()){
                Cart cart1 = carts.get(0);
                cartService.deleteCartById(cart1.getId());
            }
        }
        order.setStatus("0");
        order.setGoodsList(sb.toString());
        order.setUserId(ShiroUtils.getUserId());
        System.out.println(order);
        orderService.insertSysOrder(order);
        return AjaxResult.success();
    }



    @Autowired
    private OrderService orderService1;


    @GetMapping("/test")
    @ResponseBody
    public List<Integer> test(){
        return recommendationSystem.recommendProducts(1);
    }





    @GetMapping("/product/{id}")
    public String Product(@PathVariable Long id,ModelMap modelMap){
        Product product = iProductService.selectProductById(id);
        List<String> imglist = new ArrayList<>();
        String[] split = product.getImglist().split(",");
        for(String s:split){
            imglist.add(s);
        }
        modelMap.put("product",product);
        modelMap.put("imglist",imglist);
        return "pet/shop_details";
    }



    @Autowired
    private IPetService petService;


    @GetMapping("/pet/{id}")
    public String Pet(@PathVariable Long id,ModelMap modelMap){
        Pet pet = petService.selectPetById(id);
        modelMap.put("pet",pet);
        return "pet/blog_details";
    }

    @Autowired
    private PetResServiceImpl petResService;


    @PostMapping("/pet/order")
    @ResponseBody
    public String petorder(@RequestParam Map<String,Object> map){
        System.out.println("Received adoption request data: " + map);
        PetRes petRes = new PetRes();
        petRes.setContact(map.get("contact").toString());
        petRes.setMessage(map.get("message").toString());
        petRes.setPetName(map.get("petname").toString());
        petRes.setPetType((String) map.get("caretype"));
        try {
            petRes.setResDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse((String) map.get("date")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        petRes.setName(map.get("name").toString());
        petRes.setUserId(ShiroUtils.getUserId());
        petRes.setStatus("0");
        petResService.insertPetRes(petRes);
        return "succ";
    }


    @Autowired
    private IPetApplyService petApplyService;





    @GetMapping("/chart1")
    @ResponseBody
    public AjaxResult getchart1(){
        // Get list of orders from the database within the last 7 days
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6); // 7 days ago
        List<SysOrder> sysOrders = orderService.selectSysOrderList(new SysOrder());

        // Initialize a map to store the count of orders for each day
        Map<LocalDate, Integer> orderCounts = new HashMap<>();

        // Initialize orderCounts with 0 for each day within the last 7 days
        LocalDate date = sevenDaysAgo;
        while (!date.isAfter(today)) {
            orderCounts.put(date, 0);
            date = date.plusDays(1);
        }

        // Iterate through the orders and count the number of orders for each day
        for (SysOrder order : sysOrders) {
            // Convert java.util.Date to LocalDate
            LocalDate orderDate = order.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(orderCounts.containsKey(orderDate)){
                orderCounts.put(orderDate, orderCounts.get(orderDate) + 1);
            }
        }

        // Return the order counts as JSON response
        return AjaxResult.success(orderCounts);
    }

    @GetMapping("/chart2")
    @ResponseBody
    public AjaxResult getchart2(){
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6); // 7 days ago
        List<PetRes> sysOrders = petResService.selectPetResList(new PetRes());

        // Initialize a map to store the count of orders for each day
        Map<LocalDate, Integer> orderCounts = new HashMap<>();

        // Initialize orderCounts with 0 for each day within the last 7 days
        LocalDate date = sevenDaysAgo;
        while (!date.isAfter(today)) {
            orderCounts.put(date, 0);
            date = date.plusDays(1);
        }

        // Iterate through the orders and count the number of orders for each day
        for (PetRes order : sysOrders) {
            // Convert java.util.Date to LocalDate
            LocalDate orderDate = order.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(orderCounts.containsKey(orderDate)){
                orderCounts.put(orderDate, orderCounts.get(orderDate) + 1);
            }
        }

        // Return the order counts as JSON response
        return AjaxResult.success(orderCounts);
    }





    @PostMapping("/pet/res")
    @ResponseBody
    public String  petres(@RequestParam Map<String,Object> map){
        System.out.println("Received adoption request data: " + map);
        PetApply petApply = new PetApply();
        petApply.setContact(map.get("email").toString());
        petApply.setName(map.get("name").toString());
        petApply.setPetId(Long.valueOf(map.get("petId").toString()));
        petApply.setMessage(map.get("comment").toString());
        petApply.setStatus("0");
        //from
        petApply.setFromId(petService.selectPetById(Long.valueOf(map.get("petId").toString())).getUserId());
        petApply.setToId(ShiroUtils.getUserId());
        petApplyService.insertPetApply(petApply);
        // 返回响应
        return "Adoption request received successfully!";
    }
    @GetMapping("/adopt")
    public String adopt(@RequestParam(required = false, defaultValue = "1") Integer page, ModelMap model){
        PageHelper.startPage(page,8);
        Pet pet = new Pet();
        pet.setStatus("0");
        List<Pet> pets = petService.selectPetList(pet);
        PageInfo<Pet> info = new PageInfo<>(pets);
        model.put("info",info);
        model.put("currentPage",page);
        PageHelper.clearPage();
        return "pet/blog_grid";
    }


    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/cart")
    public String cart(ModelMap modelMap){
        Cart cart = new Cart();
        cart.setUserId(ShiroUtils.getUserId());
        List<Cart> carts = cartService.selectCartList(cart);
        List<CartFront> cartFronts = new ArrayList<>();
        for(Cart cart1:carts){
            CartFront cartFront = new CartFront();
            BeanUtils.copyProperties(cart1,cartFront);
            Product product = productService.selectProductById(cartFront.getProductId());
            cartFront.setProduct(product);
            cartFronts.add(cartFront);
        }
        modelMap.put("items",cartFronts);
        return "pet/cart";
    }

    // 订单处理
    @Autowired
    private ICartService cartService;

    @GetMapping("/addcart/{id}/{number}")
    @ResponseBody
    public AjaxResult addcart(@PathVariable Long id,@PathVariable Long number){
        //
        Cart cart = new Cart();
        cart.setUserId(ShiroUtils.getUserId());
        cart.setProductId(id);
        cart.setNumber(number);
        cartService.insertCart(cart);
        return AjaxResult.success();
    }






}
