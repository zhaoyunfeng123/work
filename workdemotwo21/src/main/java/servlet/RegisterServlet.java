package servlet;

import bean.UserBean;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 注册
 * */
@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 185275554L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        UserBean user = new UserBean();

        user.setUserName(req.getParameter("userName"));
        user.setPassword(req.getParameter("password"));
        user.setUserType("用户");
        user.setFullName(req.getParameter("fullName"));
        user.setTel(req.getParameter("tel"));
        user.setAddress(req.getParameter("address"));
        user.setCity(req.getParameter("city"));
        user.setCountry(req.getParameter("country"));
        user.setEmail(req.getParameter("email"));
        String[] hobbyValues = req.getParameterValues("hobby");
        String hobby ="";
        for(int i=0;i<hobbyValues.length;i++){
            hobby=String.join("-",hobbyValues);
        }
        user.setHobby(hobby);
        user.setProblem(req.getParameter("problem"));
        user.setAnswer(req.getParameter("answer"));
        //注册时间
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateRegister = sdf.format(date);
        user.setCreateTime(dateRegister);
        //打印提交信息
        System.out.println(user.toString());

        UserService userService = new UserServiceImpl();
        PrintWriter out = resp.getWriter();
        String result ="fail";
//        boolean userExist = userService.findUser(user.getUserName());//有问题，不能和注册同时使用
//        System.out.println("123"+userExist);
//        if (userExist){
//        用户已存在
//            out.write(result);
//            out.flush();
//            out.close();
//        }else {
        //用户不存在，则添加用户（用户注册）
        try {
            userService.register(user);
            result = "success";
            out.write(result);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
//        }
    }
}
