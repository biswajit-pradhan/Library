package com.library.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.main.model.Reader;
import com.library.main.service.ReaderService;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {
	
	@Autowired
	private ReaderService readerService;
	
	@PostMapping("/add")
	public ResponseEntity<String> insertReader(@RequestBody Reader reader){
		readerService.insertReader(reader);
		return ResponseEntity.status(HttpStatus.OK).body("Reader posted in DB");
	}
	
	@GetMapping("/allreader")
	public List<Reader> getAllPublishers(){
		List<Reader> list = readerService.getAllReader();
		return list;
	}
	
	@GetMapping("/one/reader/{rid}")
	public ResponseEntity<Object> getById(@PathVariable("rid")int rid){
		Optional<Reader> optional = readerService.getReaderById(rid);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Reader ID Given");
		
		Reader reader = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(reader);
	}
	@PutMapping("/update/{rid}")
	public ResponseEntity<String> updateReader(@RequestBody Reader readertoupdate, @PathVariable("rid") int rid ) {
		Reader reader = (Reader)readerService.getReaderById(rid).get();
		reader.setName(readertoupdate.getName());
		readerService.updateReader(reader);
		return ResponseEntity.status(HttpStatus.OK).body("Reader updated");
	}
	@DeleteMapping("/delete/{rid}")
	public ResponseEntity<String> deleteReader(@PathVariable("rid")int rid) {
		readerService.deleteReader(rid);
		return ResponseEntity.status(HttpStatus.OK).body("Reader deleted");
	}
}
