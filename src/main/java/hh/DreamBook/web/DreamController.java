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

import hh.DreamBook.domain.Dream;
import hh.DreamBook.domain.DreamRepo;
import hh.DreamBook.domain.DreamTypeRepo;
import hh.DreamBook.domain.KeywordRepo;

@CrossOrigin
@Controller
public class DreamController {

	@Autowired
	private DreamRepo dRepo;

	@Autowired
	private DreamTypeRepo dtRepo;

	@Autowired
	private KeywordRepo kRepo;

	// Index Page
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	// Login Page
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// Dreams page with list of dreams and form for adding new Dream
	@GetMapping("/dreamlist")
	public String listDreams(Model model) {

		// All dreams are fetched from the database and added to the model attribute
		model.addAttribute("dreams", dRepo.findAll());

		// for form
		model.addAttribute("dream", new Dream());
		// for choosing DreamType
		model.addAttribute("dreamTypes", dtRepo.findAll());
		// for choosing Keyword
		model.addAttribute("keywords", kRepo.findAll());
		return "dreams"; // dreams.html
	}

	// Save Dream
	// Redirect to dreams after adding a new dream with a form
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/savedream")
	public String saveDream(@Valid Dream dream, BindingResult bindingResult) {
		// check validation errors
		if (bindingResult.hasErrors()) {
			return "dreams";
		} else {
			dRepo.save(dream);
			return "redirect:dreamlist";
		}
	}

	// Delete Dream
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/deletedream/{dreamId}")
	// @PathVariable extracts id from the URI
	public String deleteDream(@PathVariable("dreamId") Long dreamId, Model model) {
		dRepo.deleteById(dreamId);
		return "redirect:../dreamlist"; // again redirect
	}

	// Edit Dream
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/editdream/{dreamId}")
	// @PathVariable extracts id from the URI
	public String editDream(@PathVariable("dreamId") Long dreamId, Model model) {
		model.addAttribute("dreamInEdit", dRepo.findById(dreamId));
		// find DreamTypes for user
		model.addAttribute("dreamTypes", dtRepo.findAll());
		// find Keywords for user
		model.addAttribute("keywords", kRepo.findAll());
		return "editdream";
	}

	// RESTful services
	// REST controller returns HTTP response body
	// while MVC controller returns a view.

	// REST All Dreams
	@GetMapping("/dreams")
	public @ResponseBody List<Dream> dreamListRest() {
		return (List<Dream>) dRepo.findAll();
	}

	// REST Find Dream by id
	@GetMapping("/dreams/{dreamId}")
	public @ResponseBody Optional<Dream> findDreamRest(@PathVariable("dreamId") Long dreamId) {
		return dRepo.findById(dreamId);
	}

	// REST Save Dream
	@PostMapping("/dreams")
	public @ResponseBody Dream saveDreamRest(@RequestBody Dream dream) {
		return dRepo.save(dream);
	}

	// REST Delete Dream
	@GetMapping("/dreams/delete/{dreamId}")
	public void deleteDream(@PathVariable("dreamId") Long dreamId) {
		dRepo.deleteById(dreamId);
	}
}
