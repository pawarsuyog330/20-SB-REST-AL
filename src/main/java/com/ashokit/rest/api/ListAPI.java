package com.ashokit.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/list")
public class ListAPI {
	List<Integer> lst = new ArrayList<>();

	@GetMapping("/read/{index}")
	public ResponseEntity<Integer> readValueAtIndex(@PathVariable Integer index) {
		return new ResponseEntity<Integer>(lst.get(index), HttpStatus.OK);
	}

	@PostMapping("/add/{value}")
	public ResponseEntity<String> addValueToList(@PathVariable Integer value) {
		lst.add(value);
		return new ResponseEntity<String>("Value Added", HttpStatus.CREATED);
	}

	@PutMapping("/update/{oldValue}/{newValue}")
	public ResponseEntity<Void> updateValueInList(@PathVariable Integer oldValue, @PathVariable Integer newValue) {
		if (lst.contains(oldValue)) {
			int index = lst.lastIndexOf(oldValue);
			lst.set(index, newValue);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{value}")
	public ResponseEntity<Void> deleteValueFromList(@PathVariable Integer value) {
		if (lst.contains(value)) {
			lst.remove(value);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}
