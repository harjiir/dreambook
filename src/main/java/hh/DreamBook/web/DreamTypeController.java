package hh.DreamBook.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.DreamBook.domain.DreamType;
import hh.DreamBook.domain.DreamTypeRepo;

@CrossOrigin
@Controller
public class DreamTypeController {

	@Autowired
	private DreamTypeRepo dtRepo;

	// All DreamTypes
	@GetMapping("/dreamtypes")
	public String listTypes(Model model) {
		// All dreams are fetched from the database and added to the model attribute
		model.addAttribute("dreamTypes", dtRepo.findAll());
		return "dreamtypes"; // dreamstypes.html
	}

	// Add DreamType
	@GetMapping("/addtype")
	public String addType(Model model) {
		model.addAttribute("dreamType", new DreamType());
		return "addtype"; // addtype.html
	}

	// Save DreamType
	// Redirect to dreamtypes after adding a new DreamType with a form
	@PostMapping("/savetype")
	public String saveType(DreamType dreamType) {
		dtRepo.save(dreamType);
		return "redirect:dreamtypes";
	}

	// Delete DreamType, only ADMIN can do this
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deletetype/{typeId}")
	// @PathVariable extracts id from the URI
	public String deleteType(@PathVariable("typeId") Long dreamId, Model model) {
		dtRepo.deleteById(dreamId);
		return "redirect:../dreamtypes"; // again redirect
	}

	// Edit DreamType, only ADMIN can do this
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edittype/{typeId}")
	// @PathVariable extracts id from the URI
	public String editType(@PathVariable("typeId") Long dreamId, Model model) {
		model.addAttribute("typeInEdit", dtRepo.findById(dreamId));
		return "edittype";
	}

	// RESTful services
	// REST controller returns HTTP response body
	// while MVC controller returns a view.

	// REST All Types
	@GetMapping("/typelist")
	public @ResponseBody List<DreamType> typeListRest() {
		return (List<DreamType>) dtRepo.findAll();
	}

	// REST Find Type by id
	@GetMapping("/typelist/{typeId}")
	public @ResponseBody Optional<DreamType> findTypeRest(@PathVariable("typeId") Long typeId) {
		return dtRepo.findById(typeId);
	}

	// REST Save Type
	@PostMapping("/typelist")
	public @ResponseBody DreamType saveTypeRest(@RequestBody DreamType dreamType) {
		return dtRepo.save(dreamType);
	}
}
