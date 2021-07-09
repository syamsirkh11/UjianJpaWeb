package com.juaracoding.ujianjpaweb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;


import com.juaracoding.ujianjpaweb.entity.CuriculumvitaeModel;
import com.juaracoding.ujianjpaweb.interfaces.CuriculumvitaeRepository;
import com.juaracoding.ujianjpaweb.util.*;



@Controller

public class FormPage {
	
	@Autowired
	CuriculumvitaeRepository curiculumvitaeRepo;
	
	@GetMapping("/")
	public String viewForm(Model model) {
		model.addAttribute("curiculumvitaemodel" , new CuriculumvitaeModel());
	//	model.addAttribute("listActivities", this.curiculumvitaeRepo.findAll());
		
		return "form1.html";
	}
	
	@GetMapping()
		
	@PostMapping("/curiculumvitae/add")
	public String addCuriculumvitae(@RequestParam("fullname")String fullname,@RequestParam("emailaddress")String emailaddress,
			@RequestParam("favoriteplatform")String favoriteplatform, @RequestParam("document") MultipartFile file,Model model) {
	//public String addCuriculumvitae(@ModelAttribute CuriculumvitaeModel curiculumvitaeModel, Model model) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		CuriculumvitaeModel curiculumvitaeModel = new CuriculumvitaeModel(0,fullname,emailaddress,favoriteplatform,fileName);
		curiculumvitaeModel.setDocument(fileName);
		this.curiculumvitaeRepo.save(curiculumvitaeModel);
		String uploadDir = "C:/cvupload/"+fileName;
		try {
			FileUplodUtil.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return "redirect:/form1";
	}

		
	}

	


