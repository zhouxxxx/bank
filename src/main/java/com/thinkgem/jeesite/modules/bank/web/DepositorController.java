package com.thinkgem.jeesite.modules.bank.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bank.service.DepositorService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "${adminPath}/bank/user")
@Controller
public class DepositorController extends BaseController{

    @Autowired
    private DepositorService depositorService;

    private User get(String id){
        return depositorService.get(id);
    }

    @RequestMapping("list")
    public String findList(User user,HttpServletRequest request, HttpServletResponse response, Model model){
        Page<User> page = depositorService.findDateList(new Page<User>(request, response), user);
        model.addAttribute("user",user);
        model.addAttribute("page",page);
        return "modules/bank/depositorList";
    }

    @RequestMapping("form")
    public String form(User user,Model model){
        User user1 = new User();
        if(get(user.getId()) != null){
            user1 =  get(user.getId());
        }
        model.addAttribute("user", user1);
        return "modules/bank/depositorForm";
    }

    @RequestMapping("save")
    public String save(User user,Model model,RedirectAttributes redirectAttributes){
        user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
        depositorService.save(user);
        addMessage(redirectAttributes, "保存储户'" + StringUtils.abbr(user.getName(),50) + "'成功");
        model.addAttribute("user", user);
        return "redirect:" + adminPath + "/bank/user/list";
    }

    @RequestMapping("check")
    @ResponseBody
    public boolean check(User user , String loginName){
        return depositorService.checkName(loginName);
    }

    @RequestMapping("rest")
    public String rest(String id,Model model,RedirectAttributes redirectAttributes){
        User user = get(id);
        user.setPassword(SystemService.entryptPassword("123456"));
        int i = depositorService.updatePassword(user);
        addMessage(redirectAttributes, "密码重置成功！重置的密码为123456'");
        return  "redirect:" + adminPath + "/bank/user/list";
    }
}
