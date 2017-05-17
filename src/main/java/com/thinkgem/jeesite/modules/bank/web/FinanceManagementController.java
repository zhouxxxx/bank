package com.thinkgem.jeesite.modules.bank.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bank.entity.Finance;
import com.thinkgem.jeesite.modules.bank.entity.FinanceDetail;
import com.thinkgem.jeesite.modules.bank.entity.Rate;
import com.thinkgem.jeesite.modules.bank.service.FinanceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@RequestMapping(value = "${adminPath}/bank/finance")
@Controller
public class FinanceManagementController extends BaseController{

    @Autowired
    private FinanceManagementService fnanceManagementService;

    private Finance get(String id){
        return fnanceManagementService.get(id);
    }

    @RequestMapping("list")
    public String findList(Finance finance,HttpServletRequest request, HttpServletResponse response, Model model){
        Page<Finance> page = fnanceManagementService.findDateList(new Page<Finance>(request, response), finance);
        model.addAttribute("finance",finance);
        model.addAttribute("page",page);
        return "modules/bank/financeManagementList";
    }


    @RequestMapping("dklist")
    public String findDkList(Finance finance,HttpServletRequest request, HttpServletResponse response, Model model){
        Page<Finance> page = fnanceManagementService.findDateList(new Page<Finance>(request, response), finance);
        model.addAttribute("finance",finance);
        model.addAttribute("page",page);
        return "modules/bank/dkManagementList";
    }

    @RequestMapping("detail")
    public String detail(Finance finance,Model model,HttpServletRequest request, HttpServletResponse response){
        Page<FinanceDetail> page = fnanceManagementService.findDeatil(new Page<FinanceDetail>(request, response), finance);
        Finance finances = fnanceManagementService.get(finance.getId());
        finances.setType(finance.getType());
        model.addAttribute("page",page);
        model.addAttribute("finances",finances);
        model.addAttribute("finance",finance);
        return "modules/bank/financeDetail";
    }

    @RequestMapping("dkDetail")
    public String dkDetail(Finance finance,Model model,HttpServletRequest request, HttpServletResponse response){
        Page<FinanceDetail> page = fnanceManagementService.findDeatil(new Page<FinanceDetail>(request, response), finance);
        Finance finances = fnanceManagementService.get(finance.getId());
        finances.setType(finance.getType());
        model.addAttribute("page",page);
        model.addAttribute("finances",finances);
        model.addAttribute("finance",finance);
        return "modules/bank/dkFinanceDetail";
    }

    @RequestMapping("drad")
    public String dradMoney(Finance finance,Model model){
        Finance finances =  get(finance.getId());
        finances.setType(finance.getType());
        model.addAttribute("finance", finances);
        return "modules/bank/drawFinance";
    }


    @RequestMapping("dk")
    public String dkMoney(Finance finance,Model model){
        Finance finances =  fnanceManagementService.findDkHkMoney(finance);
        finances.setType(finance.getType());
        model.addAttribute("finance", finances);
        if("3".equals(finance.getType())){
            return "modules/bank/dkFinance";
        }
        return "modules/bank/hkFinance";
    }

    @RequestMapping("save")
    public String save(FinanceDetail financeDetail,Model model,RedirectAttributes redirectAttributes){
        fnanceManagementService.saveMoney(financeDetail);
        addMessage(redirectAttributes, "1".equals(financeDetail.getType())?"存款成功!":"取款成功!");
        return "redirect:" + adminPath + "/bank/finance/list";
    }

    @RequestMapping("dkSave")
    public String dkSave(FinanceDetail financeDetail,Model model,RedirectAttributes redirectAttributes){
        fnanceManagementService.saveDkMoney(financeDetail);
        addMessage(redirectAttributes, "3".equals(financeDetail.getType())?"贷款成功!":"还款成功!");
        return "redirect:" + adminPath + "/bank/finance/dklist";
    }


    @RequestMapping("checkMoney")
    @ResponseBody
    public boolean checkMoney(FinanceDetail financeDetail){
        BigDecimal nowMoney = get(financeDetail.getUserId()).getMoney();
        BigDecimal saveMoney = financeDetail.getMoney();
        if(nowMoney.compareTo(saveMoney)==-1){
            return false;
        }
        return true;
    }

    @RequestMapping("checkDkMoney")
    @ResponseBody
    public boolean checkDkMoney(FinanceDetail financeDetail){
        BigDecimal nowDkMoney = get(financeDetail.getUserId()).getDkMoney();
        if(nowDkMoney.intValue()>0){
            return false;
        }
        return true;
    }

    @RequestMapping("checkHkMoney")
    @ResponseBody
    public boolean checkHkMoney(FinanceDetail financeDetail){
        BigDecimal nowHkMoney = get(financeDetail.getUserId()).getHkMoney();
        BigDecimal nowDkMoney = get(financeDetail.getUserId()).getDkMoney();
        if((nowHkMoney.add(financeDetail.getMoney())).compareTo(nowDkMoney) == 1){
            return false;
        }
        return true;
    }

    @RequestMapping("checkUserId")
    @ResponseBody
    public boolean checkUserId(String bankId,String name){
        return fnanceManagementService.findName(bankId,name);
    }

    @RequestMapping("checkBankId")
    @ResponseBody
    public boolean checkBankId(String bankId){
        return fnanceManagementService.findBankId(bankId);
    }




    @RequestMapping("monthDetail")
    public String monthDetail(Finance finance,Model model,HttpServletRequest request, HttpServletResponse response){
        Page<FinanceDetail> page = fnanceManagementService.monthDetail(new Page<FinanceDetail>(request, response), finance);
        model.addAttribute("page",page);
        model.addAttribute("finance",finance);
        return "modules/bank/financeMonthDetail";
    }

    @RequestMapping("totalDetail")
    public String totalDetail(Finance finance,Model model,HttpServletRequest request, HttpServletResponse response){
        Page<FinanceDetail> page = fnanceManagementService.totalDetail(new Page<FinanceDetail>(request, response), finance);
        model.addAttribute("page",page);
        model.addAttribute("finance",finance);
        return "modules/bank/financeDetailtotal";
    }

    @RequestMapping("settingRate")
    public String settingRate(Model model){
        Rate rate = fnanceManagementService.getNowRate();
        if(null == rate){
            rate = new Rate();
        }
        model.addAttribute("rate",rate);
        return "modules/bank/settingRate";
    }

    @RequestMapping("saveRate")
    public String saveRate(Rate rate,RedirectAttributes redirectAttributes){
        fnanceManagementService.saveRate(rate);
        addMessage(redirectAttributes, "保存成功！");
        return "redirect:" + adminPath + "/bank/finance/settingRate";
    }

}
