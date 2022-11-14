package hh.DreamBook.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.DreamBook.domain.Keyword;
import hh.DreamBook.domain.KeywordRepo;

@CrossOrigin
@Controller
public class KeywordController {

	@Autowired
	private KeywordRepo kRepo;

	// All Keywords
	@GetMapping("/keywordlist")
	public String listKeywords(Model model) {
		// All dreams are fetched from the database and added to the model attribute
		model.addAttribute("keywords", kRepo.findAll());
		// for form
		model.addAttribute("keyword", new Keyword());
		return "keywords"; // keywords.html
	}

	// Add Keyword
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addkeyword")
	public String addKeyword(Model model) {
		model.addAttribute("keyword", new Keyword());
		return "addkeyword"; // addkeyword.html
	}

	// Save Keyword
	// Redirect to keywords after adding a new Keyword with a form
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/savekeyword")
	public String saveKeyword(@Valid Keyword keyword, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "keywords";
		} else {
			kRepo.save(keyword);
			return "redirect:keywordlist";
		}
	}

	// Delete Keyword
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deletekeyword/{dreamId}")
	// @PathVariable extracts id from the URI
	public String deleteKeyword(@PathVariable("dreamId") Long dreamId, Model model) {
		kRepo.deleteById(dreamId);
		return "redirect:../keywordlist"; // again redirect
	}

	// Edit Keyword
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editkeyword/{dreamId}")
	// @PathVariable extracts id from the URI
	public String editKeyword(@PathVariable("dreamId") Long dreamId, Model model) {
		model.addAttribute("keywordInEdit", kRepo.findById(dreamId));
		return "editkeyword"; // editkeyword.html
	}

	// RESTful services
	// REST controller returns HTTP response body
	// while MVC controller returns a view.

	// REST All Keywords
	@GetMapping("/keywords")
	public @ResponseBody List<Keyword> keyListRest() {
		return (List<Keyword>) kRepo.findAll();
	}

	// REST Find Keyword by id
	@GetMapping("/keywords/{keyId}")
	public @ResponseBody Optional<Keyword> findTypeRest(@PathVariable("keyId") Long keyId) {
		return kRepo.findById(keyId);
	}

	// REST Save Keyword
	@PostMapping("/keywords")
	public @ResponseBody Keyword saveTypeRest(@RequestBody Keyword keyword) {
		return kRepo.save(keyword);
	}
}
