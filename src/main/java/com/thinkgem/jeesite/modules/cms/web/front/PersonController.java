package com.thinkgem.jeesite.modules.cms.web.front;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bank.entity.Finance;
import com.thinkgem.jeesite.modules.bank.entity.FinanceDetail;
import com.thinkgem.jeesite.modules.bank.service.FinanceManagementService;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${frontPath}")
public class PersonController extends BaseController {



	@Autowired
	private SystemService systemService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private FinanceManagementService financeManagementService;


	@RequestMapping(value = "person")
	public String listCustom(Model model,Finance finance, HttpServletRequest request, HttpServletResponse response) {
		Category category = categoryService.get("6");
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		String id = UserUtils.getUser().getId();
		if(StringUtils.isEmpty(id)){
			return "redirect:" + adminPath + "/login";
		}
		finance.setId(id);
		finance.setType("1");
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		Page<FinanceDetail> page = financeManagementService.findDeatilList(new Page<FinanceDetail>(request, response), finance);
		model.addAttribute("finance",finance);
		model.addAttribute("page",page);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/" + site.getTheme() + "/person";
	}


	@RequestMapping(value = "dkFinance")
	public String dkFinance(Model model,Finance finance, HttpServletRequest request, HttpServletResponse response) {
		Category category = categoryService.get("6");
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		String id = UserUtils.getUser().getId();
		if(StringUtils.isEmpty(id)){
			return "redirect:" + adminPath + "/login";
		}
		finance.setId(id);
		finance.setType("2");
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		Page<FinanceDetail> page = financeManagementService.findDeatilList(new Page<FinanceDetail>(request, response), finance);
		model.addAttribute("finance",finance);
		model.addAttribute("page",page);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/" + site.getTheme() + "/dkPerson";
	}


	@RequestMapping(value = "toFinance")
	public String toFinance(Model model,Finance finance, HttpServletRequest request, HttpServletResponse response) {
		Category category = categoryService.get("6");
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		String id = UserUtils.getUser().getId();
		if(StringUtils.isEmpty(id)){
			return "redirect:" + adminPath + "/login";
		}
		finance.setId(id);
		finance.setType("3");
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		Page<FinanceDetail> page = financeManagementService.findDeatilList(new Page<FinanceDetail>(request, response), finance);
		model.addAttribute("finance",finance);
		model.addAttribute("page",page);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/" + site.getTheme() + "/toPerson";
	}

	@RequestMapping(value = "changePassword")
	public String changePassword(Model model,User user,String type) {
		Category category = categoryService.get("6");
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		String id = UserUtils.getUser().getId();
		if(StringUtils.isEmpty(id)){
			return "redirect:" + adminPath + "/login";
		}
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		CmsUtils.addViewConfigAttribute(model, category);
		if("2".equals(type)){
			model.addAttribute("finacne",financeManagementService.get(id));
			return "modules/cms/front/themes/" + site.getTheme() + "/personTo";
		}
		return "modules/cms/front/themes/" + site.getTheme() + "/updatePassword";
	}


	@RequestMapping(value = "savePassword")
	public String savePassword(Model model,User user,RedirectAttributes redirectAttributes) {
		String id = UserUtils.getUser().getId();
		user.setId(id);
        systemService.updatePasswordById(user.getId(), user.getLoginName(), user.getNewPassword());
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+ Global.getFrontPath()+"/changePassword?type=1";
	}


	@RequestMapping(value = "saveToperson")
	public String saveToperson(Model model,FinanceDetail finance,RedirectAttributes redirectAttributes) {
		String id = UserUtils.getUser().getId();
		finance.setUserId(id);
		financeManagementService.saveToperson(finance);
		addMessage(redirectAttributes, "转账成功");
		return "redirect:"+ Global.getFrontPath()+"/changePassword?type=2";
	}
}
