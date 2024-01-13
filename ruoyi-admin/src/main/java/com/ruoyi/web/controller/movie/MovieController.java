package com.ruoyi.web.controller.movie;

import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.net.SocketAddress;
import java.util.Enumeration;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @GetMapping("/index")
    public String getIndex(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println("Attribute Name: " + attributeName);
        }
        session.setAttribute("user","username");
        return "movie/index";
    }

    @GetMapping("/echarts")
    public String getEcharts(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println("Attribute Name: " + attributeName);
        }
        return "movie/echarts";
    }

    @GetMapping("/blog")
    public String getBlog(){
        return "movie/bloggrid";
    }

    @GetMapping("/movies")
    public String getMovies(){
        return "movie/moviegridfw";
    }
    @GetMapping("/moviesingle")
    public String getMovieSingle(){
        return "movie/moviesingle";
    }
}
