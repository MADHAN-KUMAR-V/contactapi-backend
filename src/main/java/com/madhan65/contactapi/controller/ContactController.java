package com.madhan65.contactapi.controller;

import com.madhan65.contactapi.domain.Contact;
import com.madhan65.contactapi.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.madhan65.contactapi.constants.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public Page<Contact> getAllContact(@RequestParam(value = "page",defaultValue = "0") int page ,
                                       @RequestParam(value = "size",defaultValue = "10") int size){
        return contactService.getAllContact(page,size);
    }

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable("id") String id){
        return contactService.getContact(id);
    }

    @PostMapping
    public Contact saveContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable String id){
        contactService.deleteContact(id);
    }

    @PutMapping("/photo")
    public String uploadImage(@RequestParam("id") String id, @RequestParam("file")MultipartFile file){
        return contactService.uploadPhoto(id,file);
    }
    @GetMapping(path = "/image/{filename}" ,produces = IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException{
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
