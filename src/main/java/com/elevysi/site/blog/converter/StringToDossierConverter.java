package com.elevysi.site.blog.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.service.DossierService;

public class StringToDossierConverter implements Converter<Dossier, String> {
	
	@Autowired
	private DossierService dossierService;
	
	
//	public StringToDossierConverter(DossierService dossierService) {
//		this.dossierService = dossierService;
//	}

//	@Override
//    public Dossier convert(String dossierIDStr) {
//        int dossierID = -1;
//        try{
//        	dossierID = Integer.parseInt(dossierIDStr);
//        } catch (NumberFormatException e) {
//            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Dossier.class), dossierIDStr, null);
//        }
// 
//        Dossier dossier = dossierService.findByID(dossierID);
//        
//        return dossier;
//    }
	
//	@Override
//    public Dossier convert (String id) {
//        try {
//            int dossierID = Integer.valueOf(id);
//            return dossierService.findByID(dossierID);
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }
	
	@Override
    public String convert (Dossier dossier) {
        return dossier.getId().toString();
    }
}
