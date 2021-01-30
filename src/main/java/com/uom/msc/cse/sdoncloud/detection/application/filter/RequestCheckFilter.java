
package com.uom.msc.cse.sdoncloud.detection.application.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RequestCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String[] ignoreRoutes = new String[]{
                "/actuator/prometheus"
        };
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;

        List<String> contentType = Collections.list(req.getHeaders(HttpHeaders.CONTENT_TYPE));
        String endpoint = req.getPathInfo();

        //check ignore routes
        if(Arrays.asList(ignoreRoutes).contains(ignoreRoutes)){
            chain.doFilter(request,response);
        }
//        if(contentType.isEmpty()){
//            throw new IOException("No content type");
//        } else if(!contentType.get(0).equals("application/json")){
//            throw new IOException("Only accepts JSON");
//        }
        chain.doFilter(request,response);
    }
}
