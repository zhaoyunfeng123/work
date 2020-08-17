package servlet;

import bean.OrderBean;
import net.sf.json.JSONArray;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 查看我的订单
 * */
public class SelectAllMyOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1197468251L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("userName");
        System.out.println("orderDao:"+name);

        OrderService orderService = new OrderServiceImpl();
        List<OrderBean> list = orderService.selectAllOrder(name);
        PrintWriter out = resp.getWriter();
        if (list != null){
            JSONArray jsonArray = JSONArray.fromObject(list);
            out.print(jsonArray);
        }
        out.flush();
        out.close();
    }
}
