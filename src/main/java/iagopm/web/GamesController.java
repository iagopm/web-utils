package iagopm.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import iagopm.web.model.Link;
import iagopm.web.model.Search;

@Controller
public class GamesController {
	@Value("#{'${links}'.split(',')}")
	private List<String> links;

	@RequestMapping("/games")
	public String games(@ModelAttribute Search search, BindingResult errors, Model model) {
		List<Link> linksModel = new ArrayList<>();
		links.forEach(l -> {
			l = l.replace("*", search.getText());
			l = l.contains(";") ? l.replaceAll("\\s+", l.split(";")[1]) : l;
			l = l.split(";")[0];
			linksModel.add(new Link(l));
		});
		Collections.reverse(linksModel);
		model.addAttribute("links", linksModel);
		return "table";
	}

	@RequestMapping("/")
	public String index(@ModelAttribute Search search, BindingResult errors, Model model) {
		model.addAttribute("search", new Search());
		return "form";
	}
}