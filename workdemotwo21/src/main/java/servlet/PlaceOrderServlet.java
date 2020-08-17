package servlet;

import bean.OrderBean;
import bean.OrderDetailBean;
import bean.ShoppingCartBean;
import service.Impl.OrderDetailServiceImpl;
import service.Impl.OrderServiceImpl;
import service.Impl.ShoppingCartServiceImpl;
import service.OrderDetailService;
import service.OrderService;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 下订单
 * */
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 197468251L;
    private static String date;
    private static Long orderNum = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateOrder = sdf.format(date);
        String name = req.getParameter("userName");
        String address = req.getParameter("address");
        Double count = Double.parseDouble(req.getParameter("count"));

        //保存到订单表：order_entity
        OrderBean order = new OrderBean();
        //订单编号
        String orderId = PlaceOrderServlet.getOrderNum();
        order.setOrderId(orderId);
        order.setAddress(address);
        order.setUserName(name);
        order.setCount(count);
        order.setCreateCartTime(dateOrder);
        //具体实现
        OrderService orderService = new OrderServiceImpl();
        boolean orderServiceResult = orderService.addOrder(order);

        //保存到订单详情表：order_detail
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        List<ShoppingCartBean> list = shoppingCartService.selectAllCart(name);
        ShoppingCartBean scb = null;
        OrderDetailBean detailBean = null;
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
        for(int i = 0; i < list.size(); i++){
            scb = list.get(i);
            detailBean = new OrderDetailBean();

            detailBean.setBookId(scb.getBookId());
            detailBean.setBookName(scb.getBookName());
            detailBean.setPrice(scb.getPrice());
            detailBean.setNumber(scb.getNumber());
            Double c = scb.getPrice() * scb.getNumber();
            detailBean.setCountBook(c);
            detailBean.setOrderId(orderId);
            //具体实现
            orderDetailService.addOrderDetail(detailBean);
        }
        PrintWriter out = resp.getWriter();
        if (orderServiceResult){
            out.write("success");
        }else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }
    //生成订单编号
    public static synchronized String getOrderNum(){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if (date == null || !date.equals(str)){
            date = str;
            orderNum = 0L;
        }
        orderNum ++;
        Long orderNo = Long.parseLong((date))*10000;
        orderNo += orderNum;
        return orderNo+"";
    }
}
